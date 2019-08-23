package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class UserReader {
    private final UserRepository repository;
    private final UserConverter userConverter;

    Optional<PersonalDetailsDto> readPersonalData(String username) {
        return repository.findByUsername(username)
                .map(userConverter::userToEntity)
                .map(this::getPersonalData);
    }

    Optional<UserDto> readUser(String username) {
        return repository.findByUsername(username);
    }

    private PersonalDetailsDto getPersonalData(User user) {
        return PersonalDetailsDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(userConverter.genderToDto(user.getGender()))
                .birthDate(user.getBirthDate())
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
    }
}
