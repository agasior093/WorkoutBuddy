package com.johndoe.workoutbuddy.infrastructure.repository.inmemory;

import com.johndoe.workoutbuddy.infrastructure.repository.entity.DailyConsumptionEntity;
import com.johndoe.workoutbuddy.infrastructure.repository.entity.DietEntityConverter;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

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
