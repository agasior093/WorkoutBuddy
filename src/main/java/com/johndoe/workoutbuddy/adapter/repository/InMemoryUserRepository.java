package com.johndoe.workoutbuddy.adapter.repository;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Log
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, UserDto> users = new HashMap<>();

    public InMemoryUserRepository() {
        users.put("admin", UserDto.builder()
                .username("admin")
                .password("pass")
                .active(true)
                .roles(new String[] {"ADMIN"}).build());

        users.put("user", UserDto.builder()
                .username("user")
                .password("pass")
                .active(true)
                .roles(new String[] {"USER"})
                .personalDetails(PersonalDetailsDto.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .gender("Mężczyzna")
                        .weight(90d)
                        .height(183d)
                .build())
                .build());
    }

    @Override
    public Optional<UserDto> findUser(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public void save(UserDto user) {
        users.putIfAbsent(user.getUsername(), user);
        users.forEach((k, v) -> log.info(v.toString()));
    }
}
