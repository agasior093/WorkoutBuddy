package com.johndoe.workoutbuddy.adapter.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

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