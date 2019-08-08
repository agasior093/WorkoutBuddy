package com.johndoe.workoutbuddy.domain.email.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserActivationEmail implements EmailMessage {
    private final UUID token;
    private final String username;
    private final String receiver;
    private final String subject = "WorkoutBuddy account verification";
}
