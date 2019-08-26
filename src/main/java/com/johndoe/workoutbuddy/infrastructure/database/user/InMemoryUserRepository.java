package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.domain.user.model.Gender;
import com.johndoe.workoutbuddy.domain.user.model.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile("inmemory")
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public InMemoryUserRepository() {
        users.put("admin", User.builder()
                .username("admin")
                .password("pass")
                .active(true)
                .roles(Set.of("ADMIN")).build());

        users.put("user", User.builder()
                .username("user")
                .password("pass")
                .active(false)
                .roles(Set.of("USER"))
                .firstName("John")
                .lastName("Doe")
                .gender(Gender.MALE)
                .weight(90d)
                .height(183d)
                .build());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream().filter(user -> email.equals(user.getEmail())).findFirst();
    }

    @Override
    public User saveUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }
}
