package com.johndoe.workoutbuddy.domain.diet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class DailyConsumptionDto {
    private final String id;
    private final String username;
    private final String date;
    private final List<ConsumedProductDto> consumedProducts;
}
