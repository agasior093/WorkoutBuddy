package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.Error;
import com.johndoe.workoutbuddy.domain.Success;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public class DietFacade {

    private final DietRepository repository;
    private final DailyConsumptionCreator dailyConsumptionCreator;
    private final DailyConsumptionReader dailyConsumptionReader;

    DietFacade(DietRepository repository, ProductFacade productFacade) {
        this.repository = repository;
        this.dailyConsumptionCreator = new DailyConsumptionCreator(repository);
        this.dailyConsumptionReader = new DailyConsumptionReader(repository, productFacade);
    }

    public List<ProductDto> getDailyConsumption(String username, LocalDate date) {
        System.out.println("date " + date);
        return dailyConsumptionReader.getDailyConsumption(username, date);
    }

    public Either<Error, Success> updateDailyConsumption(String username, DailyConsumptionDto productsDto) {
        return dailyConsumptionCreator.updateDailyConsumption(username, productsDto);
    }
}
