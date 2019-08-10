package com.johndoe.workoutbuddy.adapter.repository.mongo;

import com.johndoe.workoutbuddy.adapter.repository.entity.UserEntity;
import com.johndoe.workoutbuddy.adapter.repository.mapper.UserMapper;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Log
@Repository
@AllArgsConstructor
public class MongoUserRepository implements UserRepository {
    private static final String COLLECTION_NAME = "user";

    private final MongoTemplate mongoTemplate;
    private final UserMapper mapper = new UserMapper();
    private final QueryBuilder queryBuilder = new QueryBuilder();

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(queryBuilder.usernameQuery(username), UserEntity.class)).map(mapper::toDto);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findOne(queryBuilder.emailQuery(email), UserEntity.class)).map(mapper::toDto);
    }

    @Override
    public String saveUser(UserDto user) throws RuntimeException {
        UserEntity userEntity = null;
        userEntity = mongoTemplate.save(mapper.toEntity(user));
        return userEntity.getId();
    }
}
