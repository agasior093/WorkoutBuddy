package com.johndoe.workoutbuddy.adapter.repository;

import com.johndoe.workoutbuddy.adapter.repository.entity.VerificationToken;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryVerificationTokenRepository implements VerificationTokenRepository {

    private final Map<UUID, VerificationToken> tokenMap = new HashMap<>();

    @Override
    public UUID generateVerificationToken(String username) {
        UUID uuid = UUID.randomUUID();
        tokenMap.put(uuid, VerificationToken.builder()
            .uuid(uuid)
            .username(username)
            .expirationDateTime(LocalDateTime.now().plusHours(1))
            .build());
        return uuid;
    }
}
