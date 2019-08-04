package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class ReadUserUseCase {
    private final UserRepository repository;
    private final ObjectMapper objectMapper;

    Optional<PersonalDetails> readPersonalData(String username) {
        return repository.findUser(username)
                .map(objectMapper::userToEntity)
                .map(User::getPersonalDetails);
    }
}
