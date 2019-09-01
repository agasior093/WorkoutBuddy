package com.johndoe.workoutbuddy.domain.diet.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PeriodConsumption {
    private final Double calories;
    private final Double protein;
    private final Double fat;
    private final Double carbohydrates;
    private final List<DailyConsumption> dailyConsumptions;
}
