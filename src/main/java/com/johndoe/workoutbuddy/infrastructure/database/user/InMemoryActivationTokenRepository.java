package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import com.johndoe.workoutbuddy.infrastructure.database.InMemoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Profile("inmemory")
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
