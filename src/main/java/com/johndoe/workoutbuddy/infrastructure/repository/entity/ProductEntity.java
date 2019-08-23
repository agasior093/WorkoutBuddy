package com.johndoe.workoutbuddy.infrastructure.repository.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "product")
public class ProductEntity extends BaseEntity<String> {
    private final String productName;
    private final Double calories;
    private final Double protein;
    private final Double fat;
    private final Double carbohydrates;
    private final Double weight;
}
