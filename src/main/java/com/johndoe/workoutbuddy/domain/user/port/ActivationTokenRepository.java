package com.johndoe.workoutbuddy.domain.user.port;

import com.johndoe.workoutbuddy.domain.user.model.ActivationToken;

import java.util.Optional;

public interface ActivationTokenRepository {
    String generateToken(String username);
    Optional<ActivationToken> findToken(String tokenID);
    void updateToken(ActivationToken token) throws Exception;
}
