package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class UserReader {
    private final UserRepository repository;
    private final ObjectMapper objectMapper;

    Optional<PersonalDetailsDto> readPersonalData(String username) {
        return repository.findUser(username)
                .map(objectMapper::userToEntity)
                .map(this::getPersonalData);
    }

    private PersonalDetailsDto getPersonalData(User user) {
        return PersonalDetailsDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(objectMapper.genderToDto(user.getGender()))
                .birthDate(user.getBirthDate())
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
    }
}
