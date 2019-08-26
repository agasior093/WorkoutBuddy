package com.johndoe.workoutbuddy.domain.diet.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@ToString
public class DailyConsumption {
    private final String id;
    private final String username;
    private final LocalDate date;
    private final List<ConsumedProduct> consumedProducts;
}
