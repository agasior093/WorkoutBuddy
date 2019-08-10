package com.johndoe.workoutbuddy.adapter.repository.inmemory;

import com.johndoe.workoutbuddy.adapter.repository.entity.ActivationTokenEntity;
import com.johndoe.workoutbuddy.adapter.repository.entity.ActivationTokenMapper;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryActivationTokenRepository implements ActivationTokenRepository {
    private final Map<String, ActivationTokenEntity> tokens = new HashMap<>();
    private final ActivationTokenMapper mapper = new ActivationTokenMapper();

    @Override
    public String generateToken(String username) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, ActivationTokenEntity.builder()
                .tokenID(token)
                .username(username)
                .expirationDateTime(LocalDateTime.now().plusHours(1))
                .activated(false)
                .build());
        return token;
    }

    @Override
    public Optional<ActivationTokenDto> findToken(String token) {
        return Optional.ofNullable(mapper.tokenToDto(tokens.get(token)));
    }

    @Override
    public void updateToken(ActivationTokenDto tokenDto) {
        tokens.put(tokenDto.getTokenID(), mapper.tokenToEntity(tokenDto));
    }


}
