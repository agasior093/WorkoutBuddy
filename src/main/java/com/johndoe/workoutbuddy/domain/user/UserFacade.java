package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserFacade {

    private final ReadUserUseCase readUser;
    private final RegisterUserUseCase registerUser;

    public Optional<PersonalDetailsDto> readUserPersonalDetails(String username) {
        return readUser.readPersonalData(username);
    }
}
