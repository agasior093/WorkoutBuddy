package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.email.dto.VerificationEmail;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final EmailFacade emailFacade;
    private final ObjectMapper objectMapper;

    Either<DomainError, SuccessMessage> register(RegisterUserDto registerUserDto) {
        if(userRepository.findUser(registerUserDto.getUsername()).isPresent())
            return Either.left(UserError.USERNAME_ALREADY_EXISTS);
        var userCreationResult = User.createUser(registerUserDto);
        var savedUser = userCreationResult.map(this::saveUser);
        var emailDto = savedUser.map(this::createVerificationEmail);
        return emailDto.isRight() ? emailFacade.sendUserVerificationEmail(emailDto.get())
                : Either.left(emailDto.getLeft());
    }

    private User saveUser(User user) {
        userRepository.save(objectMapper.userToDto(user));
        return user;
    }

    private VerificationEmail createVerificationEmail(User user) {
        final UUID uuid = userRepository.generateRegistrationToken(user.getUsername());
        return VerificationEmail.builder()
                .token(uuid)
                .username(user.getUsername())
                .receiver(user.getEmail())
                .build();
    }

}
