package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class DailyConsumptionReader {
    private final DietRepository repository;
    private final ProductFacade productFacade;

    List<Product> getDailyConsumption(String username, LocalDate date) {
        return repository.getDailyConsumption(username, date).map(daily ->
            matchingProducts(productFacade.getProducts(), daily.getConsumedProducts())
        ).orElse(new ArrayList<>());
    }

    private List<Product> matchingProducts(List<Product> products, List<ConsumedProduct> consumedProducts) {
        final List<Product> matchedProducts = new ArrayList<>();
        consumedProducts.forEach(consumedProduct -> {
            products.forEach(product -> {
                if(product.getId().equals(consumedProduct.getId())) matchedProducts.add(product);
            });
        });
        return matchedProducts;
    }
}
