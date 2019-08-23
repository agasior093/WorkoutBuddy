package com.johndoe.workoutbuddy.domain.product.dto;

import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ProductDto {
    private final String id;
    private final String productName;
    private final Double calories;
    private final Double protein;
    private final Double fat;
    private final Double carbohydrates;
    private final Double weight;

    @Override
    public boolean equals(Object obj) {
        ConsumedProductDto other = (ConsumedProductDto) obj;
        return other != null && other.getProductID().equals(this.getId());
    }



}
