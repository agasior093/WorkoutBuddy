package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.user.model.User;
import com.johndoe.workoutbuddy.domain.user.model.ActivationToken;
import com.johndoe.workoutbuddy.domain.user.dto.error.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


import java.time.LocalDateTime;

import static com.johndoe.workoutbuddy.common.utils.EitherUtils.*;

@Log
@RequiredArgsConstructor
class UserActivator {
    private final UserRepository userRepository;
    private final ActivationTokenRepository tokenRepository;

    Either<Error, Success> activateUser(String tokenID, String username) {
        return chain(handleToken(tokenID), handleUser(username), UserError.ACTIVATION_FAILED);
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

    private Either<Error, ActivationToken> findToken(String tokenID) {
        return tokenRepository.findToken(tokenID).map(Either::right).orElse((Either)(UserError.ACTIVATION_TOKEN_NOT_FOUND.toEitherLeft()));
    }

    private Either<Error, ActivationToken> validateToken(ActivationToken tokenDto) {
        return isValid(tokenDto) ? Either.right(tokenDto) : Either.left(UserError.EXPIRED_ACTIVATION_TOKEN);
    }

    private Either<Error, Success> deactivateToken(ActivationToken tokenDto) {
        return Try.of(() -> deactivate(tokenDto))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

    private boolean isValid(ActivationToken token) {
        return !token.isActivated() && DateUtils.now().isBefore(token.getExpirationDateTime());
    }

    private Success deactivate(ActivationToken tokenDto) throws Exception {
        var deactivatedToken = ActivationToken.builder()
                .tokenID(tokenDto.getTokenID())
                .username(tokenDto.getUsername())
                .activated(true)
                .expirationDateTime(LocalDateTime.now())
                .build();
        tokenRepository.updateToken(deactivatedToken);
        return new Success();
    }

   private Either<Error, User> findUser(String username) {
        return Option.ofOptional(userRepository.findByUsername(username)).toEither(UserError.USER_NOT_FOUND);
   }

    private Either<Error, Success> activateUser(User user) {

        return Try.of(() -> activate(user))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(UserError.PERSISTENCE_FAILED);
    }

    private Success activate(User user) throws Exception {
        var activatedUser = buildActivatedUser(user);
        userRepository.saveUser(activatedUser);
        return new Success(activatedUser.getUsername() + " successfully activated");
    }

    private User buildActivatedUser(User user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .email(user.getEmail())
                .gender(user.getGender())
                .active(true)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
    }
}
