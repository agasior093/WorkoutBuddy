package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserFacade {
    private final ReadUserUseCase readUser;
    private final RegisterUserUseCase registerUser;
    private final ConfirmRegistrationUseCase confirmRegistration;
    private final ObjectMapper objectMapper;

    public Optional<PersonalDetailsDto> readUserPersonalDetails(String username) {
        return readUser.readPersonalData(username).map(objectMapper::personalDetailsToDto);
    }

    public Either<DomainError, SuccessMessage> registerNewUser(RegisterUserDto dto) {
        return registerUser.register(dto);
    }

    public Either<DomainError, SuccessMessage> confirmRegistration(String uuid, String username) {
        return confirmRegistration.confirm(UUID.fromString(uuid), username);
    }
}
