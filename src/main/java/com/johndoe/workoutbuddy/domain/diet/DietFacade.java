package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;

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

}
