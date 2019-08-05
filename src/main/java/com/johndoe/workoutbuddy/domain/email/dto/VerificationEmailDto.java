package com.johndoe.workoutbuddy.domain.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
public class VerificationEmailDto {
    private final UUID uuid;
    private final String username;
    private final String receiver;
}
