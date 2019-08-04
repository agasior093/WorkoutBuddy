package com.johndoe.workoutbuddy.adapter.repository;

import com.johndoe.workoutbuddy.adapter.repository.entity.VerificationToken;
import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@Log
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, UserDto> users = new HashMap<>();
    private final Map<UUID, VerificationToken> tokens = new HashMap<>();

    public InMemoryUserRepository() {
        users.put("admin", UserDto.builder()
                .username("admin")
                .password("pass")
                .active(true)
                .roles(new String[] {"ADMIN"}).build());

        users.put("user", UserDto.builder()
                .username("user")
                .password("pass")
                .active(false)
                .roles(new String[] {"USER"})
                .personalDetails(PersonalDetailsDto.builder()
                        .firstName("John")
                        .lastName("Doe")
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
    public String save(UserDto user) {
        users.put(user.getUsername(), user);
        return user.getUsername();
    }

    @Override
    public UUID generateRegistrationToken(String username) {
        UUID uuid = UUID.randomUUID();
        tokens.put(uuid, VerificationToken.builder()
                .uuid(uuid)
                .username(username)
                .expirationDateTime(LocalDateTime.now().plusHours(1))
                .activated(false)
                .build());
        return uuid;
    }

    @Override
    public boolean activateRegistrationToken(UUID uuid) {
        var token = tokens.get(uuid);
        if(token.isActivated()) return false;
        else {
            tokens.put(uuid, VerificationToken.builder()
                    .uuid(uuid)
                    .username(token.getUsername())
                    .activated(true)
                    .expirationDateTime(LocalDateTime.now())
                    .build());
            return true;
        }
    }
}
