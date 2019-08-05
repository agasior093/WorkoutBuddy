package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import lombok.extern.java.Log;

import java.util.Optional;
import java.util.UUID;


class CreateEmailUseCase {
    private String VERIFICATION_BODY = "To activate your account click here http://localhost:8080/user/confirm?token={TOKEN}&username={USERNAME}";

    EmailMessage createUserVerificationEmail(UUID uuid, String username, String receiver) {
        return EmailMessage.builder()
                .receiver(receiver)
                .subject("WorkoutBuddy account verification")
                .body(VERIFICATION_BODY.replace("{TOKEN}", uuid.toString()).replace("{USERNAME}", username))
                .build();
    }
}
