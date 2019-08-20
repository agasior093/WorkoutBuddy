package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.Error;
import com.johndoe.workoutbuddy.domain.Success;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.dto.CreateUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Optional;

@Log
@RequiredArgsConstructor
class UserCreator {
    private final UserRepository userRepository;
    private final ActivationTokenRepository tokenRepository;
    private final EmailFacade emailFacade;
    private final ObjectMapper objectMapper;

    Either<Error, Success> createUser(CreateUserDto userDto) {
        var validationErrors = hasValidationErrors(userDto);
        return validationErrors.isPresent() ? Either.left(validationErrors.get()) : create(userDto);
    }

    private Either<Error, Success> create(CreateUserDto userDto) {

        return User.createUser(userDto)
                .flatMap(this::saveUser)
                .flatMap(this::generateToken)
                .flatMap(token -> sendEmail(userDto, token));
    }

    private Either<Error, Success> sendEmail(CreateUserDto userDto, String tokenID) {
        return emailFacade.sendActivationEmail(userDto.getUsername(), userDto.getEmail(), tokenID);
    }

    private Either<Error, String> generateToken(User user) {
        return Try.of(() -> tokenRepository.generateToken(user.getUsername()))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

    private Either<Error, User> saveUser(User user) {
        return Try.of(() -> save(user))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

    private User save(User user) {
        userRepository.saveUser(objectMapper.userToDto(user));
        return user;
    }

    private Optional<Error> hasValidationErrors(CreateUserDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) return Optional.of(UserError.USERNAME_ALREADY_EXISTS);
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) return Optional.of(UserError.EMAIL_ALREADY_EXISTS);
        return Optional.empty();
    }


}
