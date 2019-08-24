package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class DailyConsumptionReader {
    private final DietRepository repository;
    private final ProductFacade productFacade;

    List<ProductDto> getDailyConsumption(String username, LocalDate date) {
        return repository.getDailyConsumption(username, date).map(daily ->
            matchingProducts(productFacade.getProducts(), daily.getConsumedProducts())
        ).orElse(new ArrayList<>());
    }

    private List<ProductDto> matchingProducts(List<ProductDto> products, List<ConsumedProductDto> consumedProducts) {
        return products.stream().filter(product ->
                consumedProducts.stream()
                .anyMatch(consumedProduct ->
                        product.getId().equals(consumedProduct.getProductID())))
                .collect(Collectors.toList());
    }
}
