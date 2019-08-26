package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.model.PersonalDetails;
import com.johndoe.workoutbuddy.domain.user.model.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class UserReader {
    private final UserRepository repository;

    Optional<PersonalDetails> readPersonalData(String username) {
        return repository.findByUsername(username)
                .map(this::getPersonalData);
    }

    Optional<User> readUser(String username) {
        return repository.findByUsername(username);
    }

    private PersonalDetails getPersonalData(User user) {
        return PersonalDetails.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
    }
}
