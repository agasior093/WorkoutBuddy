package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.dto.error.DietError;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;

@Log
@RequiredArgsConstructor
class DailyConsumptionUpdater {

    private final DietRepository repository;
    private final DietConverter converter;

    Either<Error, Success> addProductToDailyConsumption(ConsumedProductDto consumedProductDto) {
        return Try.of(() -> saveOrUpdate(consumedProductDto))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(DietError.PERSISTENCE_FAILED);
    }

    Success saveOrUpdate(ConsumedProductDto consumedProduct) {
        repository.getDailyConsumption(consumedProduct.getUsername(), consumedProduct.getDate())
                .ifPresentOrElse(daily -> updateDaily(converter.toEntity(daily), consumedProduct),
                                () -> createDaily(consumedProduct));
        return new Success();
    }

    private void createDaily(ConsumedProductDto consumedProduct) {
        var newDaily = DailyConsumption.builder()
                .username(consumedProduct.getUsername())
                .date(consumedProduct.getDate())
                .consumedProducts(List.of(converter.toConsumedProductEntity(consumedProduct)))
                .build();
        repository.updateDailyConsumption(converter.toDto(newDaily));
    }

    private void updateDaily(DailyConsumption daily, ConsumedProductDto consumedProduct) {
        var products = daily.getConsumedProducts();
        products.add(converter.toConsumedProductEntity(consumedProduct));
        var newDaily = DailyConsumption.builder()
                .id(daily.getId())
                .username(consumedProduct.getUsername())
                .date(consumedProduct.getDate())
                .consumedProducts(products)
                .build();
        repository.updateDailyConsumption(converter.toDto(newDaily));
    }
}
