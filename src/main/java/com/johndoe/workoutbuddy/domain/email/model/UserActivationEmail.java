package com.johndoe.workoutbuddy.domain.email.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserActivationEmail implements EmailMessage {
    private final String token;
    private final String username;
    private final String receiver;
    private final String subject = "WorkoutBuddy account verification";
}
