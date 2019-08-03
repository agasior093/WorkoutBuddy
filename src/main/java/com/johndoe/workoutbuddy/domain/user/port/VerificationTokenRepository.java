package com.johndoe.workoutbuddy.domain.user.port;

import java.util.UUID;

public interface VerificationTokenRepository {
    UUID generateVerificationToken(String username);
}
