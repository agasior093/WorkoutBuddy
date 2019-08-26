package com.johndoe.workoutbuddy.domain.diet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ConsumedProduct {
    private final String id;
    private final Double weight;

    @Override
    public boolean equals(Object obj) {
        var other = (ConsumedProduct) obj;
        return other != null &&
                other.getId() != null && other.getId().equals(this.id) &&
                other.getWeight() != null && other.getWeight().equals(this.weight);
    }
}
