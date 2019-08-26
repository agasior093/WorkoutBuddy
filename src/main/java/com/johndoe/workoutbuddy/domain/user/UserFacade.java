package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.dto.CreateUserDto;
import com.johndoe.workoutbuddy.domain.user.model.PersonalDetails;
import com.johndoe.workoutbuddy.domain.user.model.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import io.vavr.control.Either;

import java.util.Optional;

public class UserFacade {
    private final UserReader reader;
    private final UserCreator creator;
    private final UserActivator activator;

    UserFacade(UserRepository userRepository, ActivationTokenRepository tokenRepository, EmailFacade emailFacade) {
        final UserValidator userValidator = new UserValidator(userRepository);
        this.reader = new UserReader(userRepository);
        this.creator = new UserCreator(userRepository, tokenRepository, emailFacade, userValidator);
        this.activator = new UserActivator(userRepository, tokenRepository);
    }

    public Optional<PersonalDetails> readUserPersonalData(String username) {
        return reader.readPersonalData(username);
    }

    public Optional<User> readUser(String username) {
        return reader.readUser(username);
    }

    public Either<Error, Success> createUser(CreateUserDto dto) {
        return creator.createUser(dto);
    }

    public Either<Error, Success> activateUser(String tokenID, String username) {
        return activator.activateUser(tokenID, username);
    }
}
