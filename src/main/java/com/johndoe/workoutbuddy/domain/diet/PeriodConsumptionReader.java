package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;
import com.johndoe.workoutbuddy.domain.diet.model.PeriodConsumption;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.model.Product;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class PeriodConsumptionReader {
    private final DietRepository repository;
    private final ProductFacade productFacade;

    Optional<PeriodConsumption> getWeeklyConsumption(final String username) {
        var weeklyConsumption = repository.getConsumptionFromDate(username, DateUtils.today().minusDays(6));

        return null;
    }

    private Double calculateCalories(final String param, final List<DailyConsumption> dailyList) {
       return 0d;
    }




}
