package com.johndoe.workoutbuddy.domain.user.port;

import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findUser(String username);
    void save(UserDto user);
}
