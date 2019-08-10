package com.johndoe.workoutbuddy.adapter.repository.mongo;

import com.johndoe.workoutbuddy.adapter.repository.entity.ActivationTokenEntity;
import com.johndoe.workoutbuddy.adapter.repository.entity.ActivationTokenMapper;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MongoActivationTokenRepository implements ActivationTokenRepository {
    private final MongoTemplate mongoTemplate;
    private final QueryFactory queryFactory = new QueryFactory();
    private final ActivationTokenMapper mapper = new ActivationTokenMapper();

    @Override
    public String generateToken(String username) {
        var token = mongoTemplate.save(ActivationTokenEntity.builder()
                .username(username)
                .expirationDateTime(LocalDateTime.now().plusHours(1))
                .activated(false)
                .build());
        return token.getTokenID();
    }

    @Override
    public Optional<ActivationTokenDto> findToken(String tokenID) {
       return Optional.ofNullable(mongoTemplate.findOne(queryFactory.tokenIDQuery(tokenID), ActivationTokenEntity.class))
               .map(mapper::tokenToDto);
    }

    @Override
    public void updateToken(ActivationTokenDto token) {
        mongoTemplate.save(mapper.tokenToEntity(token));
    }
}
