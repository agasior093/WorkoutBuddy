package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.infrastructure.database.MongoQueryFactory;
import com.johndoe.workoutbuddy.domain.user.model.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("mongo")
public class MongoUserRepository implements UserRepository {
    private final MongoTemplate mongoTemplate;
    private final UserEntityConverter converter = new UserEntityConverter();
    private final MongoQueryFactory queryFactory = new MongoQueryFactory();

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(queryFactory.usernameQuery(username), UserEntity.class)).map(converter::toDto);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findOne(queryFactory.emailQuery(email), UserEntity.class)).map(converter::toDto);
    }

    @Override
    public User saveUser(User user) throws RuntimeException {
        UserEntity userEntity = mongoTemplate.save(converter.toEntity(user));
        return converter.toDto(userEntity);
    }
}
