package com.johndoe.workoutbuddy.adapter.repository;

import com.johndoe.workoutbuddy.domain.user.UserRepository;
import com.johndoe.workoutbuddy.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public InMemoryUserRepository() {
        users.put("Admin", new User("Admin", "admin", new String[] {"ADMIN"}));
        users.put("User", new User("User", "pass", new String[] {"USER"}));
    }
    @Override
    public Optional<User> findUser(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
