package com.johndoe.workoutbuddy.infrastructure.database.diet;

import com.johndoe.workoutbuddy.infrastructure.database.InMemoryRepository;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
@Profile("inmemory")
public class InMemoryDietRepository extends InMemoryRepository<String, DailyConsumptionEntity> implements DietRepository {

    private final DietEntityConverter converter = new DietEntityConverter();

    @Override
    protected String generateID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void updateDailyConsumption(DailyConsumptionDto dailyConsumption) {
        if(dailyConsumption.getId() != null) {
            update(converter.toEntity(dailyConsumption));
        } else {
            save(converter.toEntity(dailyConsumption));
        }
    }

    @Override
    public Optional<DailyConsumptionDto> getDailyConsumption(String username, LocalDate date) {
        return findAll().stream().filter(elem -> elem.getUsername().equals(username) && elem.getDate().equals(date))
                .findFirst().map(elem -> converter.toDto(elem, elem.getUsername()));
    }
}
