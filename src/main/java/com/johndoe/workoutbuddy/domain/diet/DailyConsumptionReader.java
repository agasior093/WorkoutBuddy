package com.johndoe.workoutbuddy.domain.diet;

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
        final List<ProductDto> result = new ArrayList<>();
        repository.getDailyConsumption(username, date).ifPresentOrElse(daily -> {
            System.out.println(username + " has daily consumption record for " + date);
            //TODO - refactor this contains
            result.addAll(productFacade.getProducts().stream().filter(daily.getProducts()::contains).collect(Collectors.toList()));
        }, () -> {
            System.out.println(username + " has no daily consumption record for " + date);
        });

        return result;
    }
}
