package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class ConfirmRegistrationUseCase {
    private final UserRepository repository;
    private final ObjectMapper objectMapper;

    Either<DomainError, SuccessMessage> confirm(UUID uuid, String username) {
        if(!repository.activateRegistrationToken(uuid)) return Either.left(UserError.EXPIRED_REGISTRATION_TOKEN);
        var userDto = repository.findUser(username);
        if(userDto.isPresent()) {
            var user = objectMapper.userToEntity(userDto.get());
            user.activate();
            repository.save(objectMapper.userToDto(user));
            return Either.right(new SuccessMessage("Successfully activated account"));
        } else {
            return Either.left(UserError.ACTIVATION_FAILED);
        }
    }




}
