package com.johndoe.workoutbuddy.adapter.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@ToString
public class ActivationToken {
    private final UUID uuid;
    private final String username;
    private final LocalDateTime expirationDateTime;
    private final boolean activated;
}