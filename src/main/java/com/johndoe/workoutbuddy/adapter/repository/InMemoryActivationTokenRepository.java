package com.johndoe.workoutbuddy.adapter.repository;

import com.johndoe.workoutbuddy.adapter.repository.entity.ActivationToken;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class InMemoryActivationTokenRepository implements VerificationTokenRepository {
    private final Map<UUID, ActivationToken> tokens = new HashMap<>();
    private final ActivationTokenMapper mapper = new ActivationTokenMapper();

    @Override
    public UUID generateToken(String username) {
        UUID uuid = UUID.randomUUID();
        tokens.put(uuid, ActivationToken.builder()
                .uuid(uuid)
                .username(username)
                .expirationDateTime(LocalDateTime.now().plusHours(1))
                .activated(false)
                .build());
        return uuid;
    }

    @Override
    public Optional<ActivationTokenDto> findToken(UUID uuid) {
        return Optional.ofNullable(mapper.tokenToDto(tokens.get(uuid)));
    }

    @Override
    public void updateToken(ActivationTokenDto tokenDto) {
        tokens.put(tokenDto.getUuid(), mapper.tokenToEntity(tokenDto));
    }


}
