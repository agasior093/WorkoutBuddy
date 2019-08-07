package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import io.vavr.control.Either;

import java.util.Optional;
import java.util.UUID;

public class UserFacade {
    private final ReadUserUseCase readUser;
    private final RegisterUserUseCase registerUser;
    private final ConfirmRegistrationUseCase confirmRegistration;
    private final ObjectMapper objectMapper;

    UserFacade(UserRepository repository, EmailFacade emailFacade) {
        this.objectMapper = new ObjectMapper();
        this.readUser = new ReadUserUseCase(repository, objectMapper);
        this.registerUser = new RegisterUserUseCase(repository, emailFacade, objectMapper);
        this.confirmRegistration = new ConfirmRegistrationUseCase(repository, objectMapper);
    }

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
