package com.johndoe.workoutbuddy.domain.email;

import lombok.extern.java.Log;

import java.util.Optional;
import java.util.UUID;

@Log
class CreateEmailUseCase {
    private String VERIFICATION_BODY = "To activate your account click here localhost:8080/user/confirm?uuid={token}";

    EmailMessage createUserVerificationEmail(UUID uuid, String receiver) {
        log.info(uuid.toString());
        return EmailMessage.builder()
                .receiver(receiver)
                .subject("WorkoutBuddy account verification")
                .body(VERIFICATION_BODY.replace("{token}", uuid.toString()))
                .build();
    }
}
