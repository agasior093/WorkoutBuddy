package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public class DietFacade {

    private final DietRepository repository;
    private final DailyConsumptionUpdater dailyConsumptionUpdater;
    private final DailyConsumptionReader dailyConsumptionReader;
    private final DietConverter converter;

    DietFacade(DietRepository repository, ProductFacade productFacade) {
        this.repository = repository;
        this.converter = new DietConverter();
        this.dailyConsumptionUpdater = new DailyConsumptionUpdater(repository, converter);
        this.dailyConsumptionReader = new DailyConsumptionReader(repository, productFacade);
    }

    public List<ProductDto> getDailyConsumption(String username, LocalDate date) {
        return dailyConsumptionReader.getDailyConsumption(username, date);
    }

    public Either<Error, DailyConsumptionDto> addProductToDailyConsumption(ConsumedProductDto consumedProduct) {
        return dailyConsumptionUpdater.addProductToDailyConsumption(consumedProduct);
    }

    public Either<Error, DailyConsumptionDto> removeProductFromDailyConsumption(ConsumedProductDto consumedProduct) {
        return dailyConsumptionUpdater.removeProductFromDailyConsumption(consumedProduct);
    }

}
