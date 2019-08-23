package com.johndoe.workoutbuddy.domain.diet.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ConsumedProductDto {
    private final String username;
    private final LocalDate date;
    private final String productID;
    private final Double weight;
}
