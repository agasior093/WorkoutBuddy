package com.johndoe.workoutbuddy.domain.user.port;

import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;

import java.util.Optional;
import java.util.UUID;

public interface ActivationTokenRepository {
    String generateToken(String username);
    Optional<ActivationTokenDto> findToken(String tokenID);
    void updateToken(ActivationTokenDto token);
}
