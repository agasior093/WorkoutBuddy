package com.johndoe.workoutbuddy.domain.user.port;

import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<UserDto> findUser(String username);
    String save(UserDto user);
    UUID generateRegistrationToken(String username);
    boolean activateRegistrationToken(UUID uuid);
}
