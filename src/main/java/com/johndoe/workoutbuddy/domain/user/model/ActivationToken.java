package com.johndoe.workoutbuddy.domain.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ActivationToken {
    private final String tokenID;
    private final String username;
    private final LocalDateTime expirationDateTime;
    private final boolean activated;
}
