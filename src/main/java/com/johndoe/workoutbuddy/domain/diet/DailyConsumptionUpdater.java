package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.messages.Error;
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

    Either<Error, DailyConsumptionDto> addProductToDailyConsumption(ConsumedProductDto consumedProduct) {
        return repository.getDailyConsumption(consumedProduct.getUsername(), consumedProduct.getDate())
                .map(daily -> addToExistingDaily(daily, consumedProduct))
                .orElse(createNewDaily(consumedProduct));
    }

    Either<Error, DailyConsumptionDto> removeProductFromDailyConsumption(ConsumedProductDto consumedProduct) {
        return null;
    }

    private Either<Error, DailyConsumptionDto> createNewDaily(ConsumedProductDto consumedProduct) {
        return Try.of(() -> create(consumedProduct))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(DietError.PERSISTENCE_FAILED);
    }

    private Either<Error, DailyConsumptionDto> addToExistingDaily(DailyConsumptionDto daily, ConsumedProductDto consumedProduct) {
        return Try.of(() -> add(daily, consumedProduct))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(DietError.PERSISTENCE_FAILED);
    }

    private DailyConsumptionDto create(ConsumedProductDto consumedProduct) {
        var daily = DailyConsumption.builder()
                .username(consumedProduct.getUsername())
                .date(consumedProduct.getDate())
                .consumedProducts(List.of(converter.toConsumedProductEntity(consumedProduct)))
                .build();
        return repository.updateDailyConsumption(converter.toDto(daily));
    }

    private DailyConsumptionDto add(DailyConsumptionDto daily, ConsumedProductDto consumedProduct) {
        var dailyEntity = converter.toEntity(daily);
        dailyEntity.addProduct(converter.toConsumedProductEntity(consumedProduct));
        return repository.updateDailyConsumption(converter.toDto(dailyEntity));
    }


}
