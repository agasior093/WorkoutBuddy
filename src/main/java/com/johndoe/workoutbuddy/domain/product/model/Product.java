package com.johndoe.workoutbuddy.domain.product.model;

import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Product {
    private final String id;
    private final String productName;
    private final Double calories;
    private final Double protein;
    private final Double fat;
    private final Double carbohydrates;
    private final Double weight;

    @Override
    public boolean equals(Object obj) {
        var other = (Product) obj;
        return other != null &&
                other.getId() != null && other.getId().equals(this.id) &&
                other.getWeight() != null && other.getWeight().equals(this.weight);
    }
}
