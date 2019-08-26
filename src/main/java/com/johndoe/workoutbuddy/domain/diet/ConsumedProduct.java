package com.johndoe.workoutbuddy.domain.diet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
class ConsumedProduct {
    private final String productID;
    private final Double weight;
}
