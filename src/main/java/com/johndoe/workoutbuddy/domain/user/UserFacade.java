package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserFacade {
    private final ReadUserUseCase readUser;
    private final RegisterUserUseCase registerUser;

    public Optional<PersonalDetailsDto> readUserPersonalDetails(String username) {
        return readUser.readPersonalData(username);
    }

    public Either<DomainError, SuccessMessage> registerNewUser(RegisterUserDto dto) {
        return registerUser.register(dto);
    }
}
