package com.johndoe.workoutbuddy.infrastructure.database.diet;

import com.johndoe.workoutbuddy.infrastructure.database.InMemoryRepository;
import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
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
    public DailyConsumption updateDailyConsumption(DailyConsumption dailyConsumption) {
        if(dailyConsumption.getId() != null) {
            return converter.toDto(update(converter.toEntity(dailyConsumption)));
        } else {
            return converter.toDto(save(converter.toEntity(dailyConsumption)));
        }
    }

    @Override
    public Optional<DailyConsumption> getDailyConsumption(String username, LocalDate date) {
        return findAll().stream().filter(elem -> elem.getUsername().equals(username) && elem.getDate().equals(date))
                .findFirst().map(converter::toDto);
    }

    @Override
    public List<DailyConsumption> getConsumptionFromDate(String username, LocalDate date) {
        return null;
    }
}
