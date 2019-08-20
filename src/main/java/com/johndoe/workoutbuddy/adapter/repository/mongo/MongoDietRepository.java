package com.johndoe.workoutbuddy.adapter.repository.mongo;

import com.johndoe.workoutbuddy.adapter.repository.entity.DailyConsumptionEntity;
import com.johndoe.workoutbuddy.adapter.repository.entity.DietEntityConverter;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MongoDietRepository implements DietRepository {

    private final MongoTemplate mongoTemplate;
    private final DietEntityConverter converter = new DietEntityConverter();
    private final QueryFactory queryFactory = new QueryFactory();

    @Override
    public void updateDailyConsumption(String username, DailyConsumptionDto productsDto) {
        mongoTemplate.save(converter.toEntity(productsDto, username));
    }

    @Override
    public Optional<DailyConsumptionDto> getDailyConsumption(String username, LocalDate date) {
        return Optional.ofNullable(mongoTemplate.findOne(queryFactory.usernameDateQuery(username, date), DailyConsumptionEntity.class))
                .map(result -> converter.toDto(result, username));
    }
}
