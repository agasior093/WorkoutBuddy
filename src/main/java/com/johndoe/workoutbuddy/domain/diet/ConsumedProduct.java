package com.johndoe.workoutbuddy.domain.diet;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@RequiredArgsConstructor
public class ConsumedProduct {
    private final String productID;
    private final Double weight;

    @Override
    public boolean equals(Object obj) {
        var other = (ConsumedProduct)obj;
        return other != null && this.productID.equals(other.productID) && this.weight.equals(other.weight);
    }
}
