package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.infrastructure.database.MongoQueryFactory;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("mongo")
public class MongoActivationTokenRepository implements ActivationTokenRepository {
    private final MongoTemplate mongoTemplate;
    private final MongoQueryFactory queryFactory = new MongoQueryFactory();
    private final ActivationTokenConverter mapper = new ActivationTokenConverter();

    @Override
    public String generateToken(String username) {
        var token = mongoTemplate.save(ActivationTokenEntity.builder()
                .username(username)
                .expirationDateTime(DateUtils.now().plusHours(1))
                .activated(false)
                .build());
        return token.getId();
    }

    @Override
    public Optional<ActivationTokenDto> findToken(String tokenID) {
       return Optional.ofNullable(mongoTemplate.findOne(queryFactory.tokenIDQuery(tokenID), ActivationTokenEntity.class))
               .map(mapper::toDto);
    }

    @Override
    public void updateToken(ActivationTokenDto token) {
        mongoTemplate.save(mapper.toEntity(token));
    }
}
