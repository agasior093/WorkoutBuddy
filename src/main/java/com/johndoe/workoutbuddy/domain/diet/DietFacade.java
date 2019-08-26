package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.domain.diet.dto.UpdateDailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.model.Product;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public class DietFacade {

    private final DietRepository repository;
    private final DailyConsumptionUpdater dailyConsumptionUpdater;
    private final DailyConsumptionReader dailyConsumptionReader;

    DietFacade(DietRepository repository, ProductFacade productFacade) {
        this.repository = repository;
        this.dailyConsumptionUpdater = new DailyConsumptionUpdater(repository);
        this.dailyConsumptionReader = new DailyConsumptionReader(repository, productFacade);
    }

    public List<Product> getDailyConsumption(String username, LocalDate date) {
        return dailyConsumptionReader.getDailyConsumption(username, date);
    }

    public Either<Error, DailyConsumption> addProductToDailyConsumption(UpdateDailyConsumptionDto updateDailyConsumption) {
        return dailyConsumptionUpdater.addProductToDailyConsumption(updateDailyConsumption);
    }

    public Either<Error, DailyConsumption> removeProductFromDailyConsumption(UpdateDailyConsumptionDto updateDailyConsumption) {
        return dailyConsumptionUpdater.removeProductFromDailyConsumption(null);
    }

}
