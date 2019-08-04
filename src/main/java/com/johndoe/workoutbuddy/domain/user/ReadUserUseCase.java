package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class ReadUserUseCase {
    private final UserRepository repository;

    Optional<PersonalDetailsDto> readPersonalData(String username) {
        return repository.findUser(username)
                .map(User::fromDto)
                .map(User::getPersonalDetails)
                .map(PersonalDetails::toDto);
    }
}
