package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.domain.diet.dto.UpdateDailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;
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

    Either<Error, DailyConsumption> addProductToDailyConsumption(UpdateDailyConsumptionDto dailyDto) {
        return repository.getDailyConsumption(dailyDto.getUsername(), dailyDto.getDate())
                .map(daily -> addToDaily(daily, dailyDto.getProduct()))
                .orElseGet(() -> createNewDaily(dailyDto));
    }

    Either<Error, DailyConsumption> removeProductFromDailyConsumption(UpdateDailyConsumptionDto dailyDto) {
        return repository.getDailyConsumption(dailyDto.getUsername(), dailyDto.getDate())
                .map(daily -> removeFromDaily(daily, dailyDto.getProduct()))
                .orElseGet(()  -> Either.left(DietError.DAILY_RECORD_NOT_FOUND));
    }


    private Either<Error, DailyConsumption> createNewDaily(UpdateDailyConsumptionDto dailyDto) {
        return Try.of(() -> create(dailyDto))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(DietError.PERSISTENCE_FAILED);
    }

    private Either<Error, DailyConsumption> addToDaily(DailyConsumption daily, ConsumedProduct product) {
        return Try.of(() -> add(daily, product))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(DietError.PERSISTENCE_FAILED);
    }

    private Either<Error, DailyConsumption> removeFromDaily(DailyConsumption daily, ConsumedProduct product) {
        return Try.of(() -> remove(daily, product))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(DietError.PERSISTENCE_FAILED);
    }

    private DailyConsumption create(UpdateDailyConsumptionDto dailyDto) {
        var daily = DailyConsumption.builder()
                .username(dailyDto.getUsername())
                .date(dailyDto.getDate())
                .consumedProducts(List.of(dailyDto.getProduct()))
                .build();
        return repository.updateDailyConsumption(daily);
    }

    private DailyConsumption add(DailyConsumption daily, ConsumedProduct consumedProduct) {
        daily.getConsumedProducts().add(consumedProduct);
        return repository.updateDailyConsumption(daily);
    }

    private DailyConsumption remove(DailyConsumption daily, ConsumedProduct product) {
        daily.getConsumedProducts().remove(product);
        return repository.updateDailyConsumption(daily);
    }
}
