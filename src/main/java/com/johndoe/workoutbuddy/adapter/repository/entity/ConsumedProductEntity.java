package com.johndoe.workoutbuddy.adapter.repository.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsumedProductEntity {
    private final String productID;
    private final Double weight;
}
