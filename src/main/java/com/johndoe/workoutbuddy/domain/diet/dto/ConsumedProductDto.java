package com.johndoe.workoutbuddy.domain.diet.dto;

import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ConsumedProductDto {
    private final String productID;
    private final Double weight;

    @Override
    public boolean equals(Object obj) {
        ConsumedProductDto other = (ConsumedProductDto)obj;
        return other != null && other.getProductID().equals(this.getProductID());
    }
}
