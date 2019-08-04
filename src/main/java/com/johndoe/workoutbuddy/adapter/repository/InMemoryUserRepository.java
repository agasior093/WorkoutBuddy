package com.johndoe.workoutbuddy.adapter.repository;

import com.johndoe.workoutbuddy.domain.user.entity.Gender;
import com.johndoe.workoutbuddy.domain.user.entity.PersonalDetails;
import com.johndoe.workoutbuddy.domain.user.entity.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public InMemoryUserRepository() {
        users.put("admin", User.builder()
                .username("admin")
                .password("pass")
                .roles(new String[] {"ADMIN"}).build());

        users.put("user", User.builder()
                .username("user")
                .password("pass")
                .roles(new String[] {"USER"})
                .personalDetails(PersonalDetails.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .gender(Gender.MALE)
                        .height(183.0)
                        .weight(90.0)
                        .birthDate(LocalDate.of(1993, 5, 20))
                .build())
                .build());
    }

    @Override
    public Optional<User> findUser(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public void save(User user) {
        users.putIfAbsent(user.getUsername(), user);
    }
}
