package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.dto.CreateUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import io.vavr.control.Either;

import java.util.Optional;

public class UserFacade {
    private final UserReader reader;
    private final UserCreator creator;
    private final UserActivator activator;
    private final UserConverter mapper;

    UserFacade(UserRepository userRepository, ActivationTokenRepository tokenRepository, EmailFacade emailFacade) {
        this.mapper = new UserConverter();
        this.reader = new UserReader(userRepository, mapper);
        this.creator = new UserCreator(userRepository, tokenRepository, emailFacade, mapper);
        this.activator = new UserActivator(userRepository, tokenRepository, mapper);
    }

    public Optional<PersonalDetailsDto> readUserPersonalData(String username) {
        return reader.readPersonalData(username);
    }

    public Optional<UserDto> readUser(String username) {
        return reader.readUser(username);
    }

    public Either<Error, Success> createUser(CreateUserDto dto) {
        return creator.createUser(dto);
    }

    public Either<Error, Success> activateUser(String tokenID, String username) {
        return activator.activateUser(tokenID, username);
    }
}
