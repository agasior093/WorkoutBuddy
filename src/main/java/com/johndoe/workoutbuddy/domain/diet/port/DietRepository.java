package com.johndoe.workoutbuddy.domain.diet.port;

import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;

import java.time.LocalDate;
import java.util.Optional;

public interface DietRepository {
    DailyConsumptionDto updateDailyConsumption(DailyConsumptionDto dailyConsumption);
    Optional<DailyConsumptionDto> getDailyConsumption(String username, LocalDate date);
}
