package com.johndoe.workoutbuddy.infrastructure.repository.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Document(collection = "activationToken")
public class ActivationTokenEntity {
    @Id
    private String tokenID;
    private final String username;
    private final LocalDateTime expirationDateTime;
    private final boolean activated;
}