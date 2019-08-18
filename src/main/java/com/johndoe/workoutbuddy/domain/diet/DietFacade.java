package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.common.Error;
import com.johndoe.workoutbuddy.domain.common.Success;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductsDto;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DietFacade {

    private final DailyConsumptionCreator dailyConsumptionCreator;

    public Either<Error, Success> getConsumedProducts(String userID, LocalDate date) {
        return null;
    }

    public Either<Error, Success> updateDailyConsumption(String username, ConsumedProductsDto productsDto) {
        return dailyConsumptionCreator.addProducts(username, productsDto);
    }
}
