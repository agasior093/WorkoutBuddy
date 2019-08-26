package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.dto.CreateUserDto;
import com.johndoe.workoutbuddy.domain.user.model.User;
import com.johndoe.workoutbuddy.domain.user.dto.error.UserError;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;

@Log
@RequiredArgsConstructor
class UserCreator {
    private final UserRepository userRepository;
    private final ActivationTokenRepository tokenRepository;
    private final EmailFacade emailFacade;
    private final UserValidator userValidator;

    Either<Error, Success> createUser(CreateUserDto userDto) {
        return userValidator.validate(userDto)
                .flatMap(this::createAndSave)
                .flatMap(this::generateToken)
                .flatMap(token -> sendEmail(userDto, token));
    }

    private Either<Error, User> createAndSave(CreateUserDto createUserDto) {
        var newUser = buildNewUser(createUserDto);
        return Try.of(() -> userRepository.saveUser(newUser))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

    private User buildNewUser(CreateUserDto createUserDto) {
        return User.builder()
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .email(createUserDto.getEmail())
                .active(false)
                .roles(Set.of("USER"))
                .build();
    }

    private Either<Error, Success> sendEmail(CreateUserDto userDto, String tokenID) {
        return emailFacade.sendActivationEmail(userDto.getUsername(), userDto.getEmail(), tokenID);
    }

    private Either<Error, String> generateToken(User user) {
        return Try.of(() -> tokenRepository.generateToken(user.getUsername()))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

}
