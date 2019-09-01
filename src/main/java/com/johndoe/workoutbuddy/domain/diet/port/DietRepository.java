package com.johndoe.workoutbuddy.domain.diet.port;

import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DietRepository {
    DailyConsumption updateDailyConsumption(DailyConsumption dailyConsumption);
    Optional<DailyConsumption> getDailyConsumption(String username, LocalDate date);
    List<DailyConsumption> getConsumptionFromDate(String username, LocalDate date);
}
