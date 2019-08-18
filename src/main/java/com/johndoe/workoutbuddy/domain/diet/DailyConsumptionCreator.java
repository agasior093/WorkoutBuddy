package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.adapter.repository.entity.DailyConsumptionEntity;
import com.johndoe.workoutbuddy.domain.common.Error;
import com.johndoe.workoutbuddy.domain.common.Success;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log
@RequiredArgsConstructor
class DailyConsumptionCreator {

    private final DietRepository repository;

    Either<Error, Success> updateDailyConsumption(String username, DailyConsumptionDto productsDto) {
        var date = LocalDate.parse(productsDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        repository.getDailyConsumption(username, date).ifPresentOrElse(d -> {
           repository.updateDailyConsumption(username, update(d.getId(), username, productsDto));
        }, () -> {
            repository.updateDailyConsumption(username, save(username, productsDto));
        });
        return Either.right(new Success());
    }

    DailyConsumptionDto save(String username, DailyConsumptionDto dto) {
        return DailyConsumptionDto.builder()
                .date(dto.getDate())
                .products(dto.getProducts())
                .username(username)
                .build();
    }

    DailyConsumptionDto update(String id, String username, DailyConsumptionDto dto) {
        return DailyConsumptionDto.builder()
                .id(id)
                .date(dto.getDate())
                .products(dto.getProducts())
                .username(username)
                .build();
    }
}
