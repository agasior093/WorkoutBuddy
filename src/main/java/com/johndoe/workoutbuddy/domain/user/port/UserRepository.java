package com.johndoe.workoutbuddy.domain.user.port;

import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findByUsername(String username);
    Optional<UserDto> findByEmail(String email);
    String saveUser(UserDto user) throws RuntimeException;

}
