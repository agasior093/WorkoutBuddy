package com.johndoe.workoutbuddy.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDto {
    private final String id;
    private final String productName;
    private final Double calories;
    private final Double protein;
    private final Double fat;
    private final Double carbohydrates;
    private final Double weight;

}
