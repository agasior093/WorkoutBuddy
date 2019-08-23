package com.johndoe.workoutbuddy.infrastructure.repository.user;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import com.johndoe.workoutbuddy.infrastructure.repository.InMemoryRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class InMemoryActivationTokenRepository extends InMemoryRepository<String, ActivationTokenEntity> implements ActivationTokenRepository {
    private final ActivationTokenConverter converter = new ActivationTokenConverter();

    @Override
    protected String generateID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generateToken(String username) {
        return save(ActivationTokenEntity.builder()
                .username(username)
                .expirationDateTime(DateUtils.now().plusHours(1))
                .activated(false)
                .build());
    }

    @Override
    public Optional<ActivationTokenDto> findToken(String id) {
        return findByID(id).map(converter::toDto);
    }

    @Override
    public void updateToken(ActivationTokenDto tokenDto) {
        update(converter.toEntity(tokenDto));
    }
}
