package com.johndoe.workoutbuddy.domain.user.port;

import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;

import java.util.Optional;
import java.util.UUID;

public interface ActivationTokenRepository {
    UUID generateToken(String username);
    Optional<ActivationTokenDto> findToken(UUID uuid);
    void updateToken(ActivationTokenDto token);
}
