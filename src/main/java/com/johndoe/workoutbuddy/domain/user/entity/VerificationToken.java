package com.johndoe.workoutbuddy.domain.user.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class VerificationToken {
    private final UUID uuid;
    private final String username;
    private final LocalDateTime expirationDateTime;
}
