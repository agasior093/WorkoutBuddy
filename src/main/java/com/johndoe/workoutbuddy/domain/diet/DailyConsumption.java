package com.johndoe.workoutbuddy.domain.diet;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Builder
@Getter
class DailyConsumption {
    private final String id;
    private final String username;
    private final LocalDate date;
    private final List<ConsumedProduct> consumedProducts;

    public void addProduct(ConsumedProduct product) {
        this.consumedProducts.add(product);
    }

    public void removeProduct(ConsumedProduct product) {
        this.consumedProducts.remove(product);
    }
}
