package com.johndoe.workoutbuddy.domain.user.port;

import com.johndoe.workoutbuddy.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUser(String username);
    void save(User user);
}
