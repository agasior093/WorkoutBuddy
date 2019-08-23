package com.johndoe.workoutbuddy.infrastructure.database.diet;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class ConsumedProductEntity {
    private final String productID;
    private final Double weight;
}
