package com.johndoe.workoutbuddy.domain.diet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProductDto {
    private final String productID;
    private final Double weight;
}
