package com.johndoe.workoutbuddy.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ActivationTokenDto {
    private final UUID uuid;
    private final String username;
    private final LocalDateTime expirationDateTime;
    private final boolean activated;
}
