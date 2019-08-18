package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.common.Error;
import com.johndoe.workoutbuddy.domain.common.Success;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductsDto;
import io.vavr.control.Either;
import lombok.extern.java.Log;

@Log
class DailyConsumptionCreator {

    Either<Error, Success> addProducts(String userID, ConsumedProductsDto productsDto) {
        log.info(userID);
        productsDto.getProducts().forEach(p -> log.info(p.toString()));
        return Either.right(new Success());
    }
}
