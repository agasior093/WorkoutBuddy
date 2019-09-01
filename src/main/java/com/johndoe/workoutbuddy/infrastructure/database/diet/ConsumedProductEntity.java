package com.johndoe.workoutbuddy.infrastructure.database.diet;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
class ConsumedProductEntity {
    private final String productID;
    private final Double weight;
}
