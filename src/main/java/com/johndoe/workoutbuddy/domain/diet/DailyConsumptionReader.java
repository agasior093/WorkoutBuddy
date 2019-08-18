package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.common.Error;
import com.johndoe.workoutbuddy.domain.common.Success;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import io.vavr.control.Either;
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
        repository.getDailyConsumption(username, date).ifPresentOrElse(d -> {
            result.addAll(productFacade.getProducts().stream().filter(d.getProducts()::contains).collect(Collectors.toList()));
        }, () -> {
            log.info("nothing to show");
        });

        return result;
    }
}
