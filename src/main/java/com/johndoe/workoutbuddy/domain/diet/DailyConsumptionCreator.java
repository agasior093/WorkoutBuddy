package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.Error;
import com.johndoe.workoutbuddy.domain.Success;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Log
@RequiredArgsConstructor
class DailyConsumptionCreator {

    private final DietRepository repository;

    Either<Error, Success> updateDailyConsumption(String username, DailyConsumptionDto productsDto) {
        var date = LocalDate.parse(productsDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.UK));
        repository.getDailyConsumption(username, date).ifPresentOrElse(d -> {
            System.out.println("Updating daily consumption record " + username + " on date " + productsDto.getDate());
            //TODO - parameters passed to this method
           repository.updateDailyConsumption(username, update(d.getId(), username, productsDto));
        }, () -> {
            System.out.println("Creating new daily consumption record " + username + " on date " + productsDto.getDate());
            repository.updateDailyConsumption(username, save(username, productsDto));
        });
        //TODO - error handling
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
