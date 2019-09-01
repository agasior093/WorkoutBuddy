package com.johndoe.workoutbuddy.domain.diet.dto;

import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UpdateDailyConsumptionDto {
    private final String username;
    private final LocalDate date;
    private final ConsumedProduct product;
}

