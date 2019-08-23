package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;
import com.johndoe.workoutbuddy.domain.user.dto.error.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@Log
@RequiredArgsConstructor
class UserActivator {
    private final UserRepository userRepository;
    private final ActivationTokenRepository tokenRepository;
    private final UserConverter mapper;

    Either<Error, Success> activateUser(String tokenID, String username) {
       return handleToken(tokenID).orElse(handleUser(username));
    }

    private Either<Error, Success> handleToken(String tokenID) {
        return findToken(tokenID)
                .flatMap(this::validateToken)
                .flatMap(this::deactivateToken);
    }

    private Either<Error, Success> handleUser(String username) {
        return findUser(username)
                .flatMap(this::activateUser);
    }

    private Either<Error, ActivationTokenDto> findToken(String tokenID) {
        return Option.ofOptional(tokenRepository.findToken(tokenID)).toEither(UserError.ACTIVATION_TOKEN_NOT_FOUND);
    }

    private Either<Error, ActivationTokenDto> validateToken(ActivationTokenDto tokenDto) {
        return isValid(tokenDto) ? Either.right(tokenDto) : Either.left(UserError.EXPIRED_ACTIVATION_TOKEN);
    }

    private Either<Error, Success> deactivateToken(ActivationTokenDto tokenDto) {
        return Try.of(() -> deactivate(tokenDto))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

    private boolean isValid(ActivationTokenDto token) {
        return !token.isActivated() && token.getExpirationDateTime().isBefore(LocalDateTime.now());
    }

    private Success deactivate(ActivationTokenDto tokenDto) throws RuntimeException {
        var deactivatedToken = ActivationTokenDto.builder()
                .tokenID(tokenDto.getTokenID())
                .username(tokenDto.getUsername())
                .activated(true)
                .expirationDateTime(LocalDateTime.now())
                .build();
        tokenRepository.updateToken(deactivatedToken);
        return new Success();
    }

   private Either<Error, UserDto> findUser(String username) {
        return Option.ofOptional(userRepository.findByUsername(username)).toEither(UserError.USER_NOT_FOUND);
   }

    private Either<Error, Success> activateUser(UserDto userDto) {
        var user = mapper.userToEntity(userDto);
        user.activate();
        return Try.of(() -> activate(mapper.userToDto(user)))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

    private Success activate(UserDto userDto) {
        userRepository.saveUser(userDto);
        return new Success(userDto.getUsername() + " successfully activated");
    }
}
