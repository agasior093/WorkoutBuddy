package com.johndoe.workoutbuddy.infrastructure.database.diet;

import com.johndoe.workoutbuddy.infrastructure.database.MongoQueryFactory;
import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Profile("mongo")
public class MongoDietRepository implements DietRepository {

    private final MongoTemplate mongoTemplate;
    private final DietEntityConverter converter = new DietEntityConverter();
    private final MongoQueryFactory queryFactory = new MongoQueryFactory();

    @Override
    public DailyConsumption updateDailyConsumption(DailyConsumption productsDto) {
        return converter.toDto(mongoTemplate.save(converter.toEntity(productsDto)));
    }

    @Override
    public Optional<DailyConsumption> getDailyConsumption(String username, LocalDate date) {
        return Optional.ofNullable(mongoTemplate.findOne(queryFactory.usernameDateQuery(username, date), DailyConsumptionEntity.class))
                .map(converter::toDto);
    }

    @Override
    public List<DailyConsumption> getConsumptionFromDate(String username, LocalDate date) {
        //TODO
        return null;
    }
}
