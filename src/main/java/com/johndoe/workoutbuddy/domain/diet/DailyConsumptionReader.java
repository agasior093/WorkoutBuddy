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
@Log
class DailyConsumptionReader {
    private final DietRepository repository;
    private final ProductFacade productFacade;

    List<ProductDto> getDailyConsumption(String username, LocalDate date) {
        return repository.getDailyConsumption(username, date).map(daily ->
            matchingProducts(productFacade.getProducts(), daily.getConsumedProducts())
        ).orElse(new ArrayList<>());
    }

    private List<ProductDto> matchingProducts(List<ProductDto> products, List<ConsumedProductDto> consumedProducts) {
        final List<ProductDto> matchedProducts = new ArrayList<>();
        consumedProducts.forEach(consumedProduct -> {
            products.forEach(product -> {
                if(product.getId().equals(consumedProduct.getProductID())) matchedProducts.add(product);
            });
        });
        return matchedProducts;
    }
}
