package com.johndoe.workoutbuddy.adapter.repository.mongo;

import com.johndoe.workoutbuddy.adapter.repository.entity.UserEntity;
import com.johndoe.workoutbuddy.adapter.repository.entity.UserMapper;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MongoUserRepository implements UserRepository {
    private final MongoTemplate mongoTemplate;
    private final UserMapper mapper = new UserMapper();
    private final QueryFactory queryFactory = new QueryFactory();

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(queryFactory.usernameQuery(username), UserEntity.class)).map(mapper::toDto);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findOne(queryFactory.emailQuery(email), UserEntity.class)).map(mapper::toDto);
    }

    @Override
    public String saveUser(UserDto user) throws RuntimeException {
        UserEntity userEntity = mongoTemplate.save(mapper.toEntity(user));
        return userEntity.getId();
    }
}
