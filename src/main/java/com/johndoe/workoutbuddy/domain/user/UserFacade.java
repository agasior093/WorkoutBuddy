package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import io.vavr.control.Either;

import java.util.Optional;
import java.util.UUID;

public class UserFacade {
    private final UserReader reader;
    private final UserCreator creator;
    private final UserActivator activator;
    private final ObjectMapper mapper;

    UserFacade(UserRepository userRepository, ActivationTokenRepository tokenRepository, EmailFacade emailFacade) {
        this.mapper = new ObjectMapper();
        this.reader = new UserReader(userRepository, mapper);
        this.creator = new UserCreator(userRepository, tokenRepository, emailFacade, mapper);
        this.activator = new UserActivator(userRepository, tokenRepository, mapper);
    }

    public Optional<PersonalDetailsDto> readUserPersonalData(String username) {
        return reader.readPersonalData(username);
    }

    public Either<DomainError, SuccessMessage> createUser(RegisterUserDto dto) {
        return creator.createUser(dto);
    }

    public Either<DomainError, SuccessMessage> activateUser(String uuid, String username) {
        return activator.activate(UUID.fromString(uuid), username);
    }
}
