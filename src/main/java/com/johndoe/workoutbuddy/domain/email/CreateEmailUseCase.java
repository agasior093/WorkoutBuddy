package com.johndoe.workoutbuddy.domain.email;

import java.util.Optional;
import java.util.UUID;

class CreateEmailUseCase {
    private String VERIFICATION_BODY = "To activate your account click here localhost:8080/user/confirm?uuid={token}";

    EmailMessage createUserVerificationEmail(UUID uuid, String receiver) {
        return EmailMessage.builder()
                .receiver(receiver)
                .subject("WorkoutBuddy account verification")
                .body(VERIFICATION_BODY.replace("{token}", uuid.toString()))
                .build();
    }
}
