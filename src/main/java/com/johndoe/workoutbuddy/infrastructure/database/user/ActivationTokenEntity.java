package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.infrastructure.database.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


//TODO - find a way to test this without making this class public
@Builder
@Getter
@Document(collection = "activationToken")
@ToString
public class ActivationTokenEntity extends BaseEntity<String> {
    private final String username;
    private final LocalDateTime expirationDateTime;
    private final boolean activated;
}