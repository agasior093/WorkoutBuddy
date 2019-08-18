package com.johndoe.workoutbuddy.domain.diet.port;

import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;

import java.time.LocalDate;
import java.util.Optional;

public interface DietRepository {
    void updateDailyConsumption(String username, DailyConsumptionDto productsDto);
    Optional<DailyConsumptionDto> getDailyConsumption(String username, LocalDate date);
}
