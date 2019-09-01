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
import java.util.Objects;
import java.util.stream.Collectors;

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
        return consumedProducts.stream()
                .map(consumedProductDto -> products.stream()
                        .filter(productDto -> productDto.getId().equals(consumedProductDto.getId()) &&
                                productDto.getWeight().equals(consumedProductDto.getWeight()))
                        .findAny().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
