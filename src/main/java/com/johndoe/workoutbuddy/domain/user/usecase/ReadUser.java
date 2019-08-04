package com.johndoe.workoutbuddy.domain.user.usecase;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.entity.PersonalDetails;
import com.johndoe.workoutbuddy.domain.user.entity.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
public class ReadUser {
    private final UserRepository repository;

    public Optional<PersonalDetailsDto> readPersonalData(String username) {
        return repository.findUser(username)
                .map(User::getPersonalDetails)
                .map(PersonalDetailsDto::new);
    }
}
