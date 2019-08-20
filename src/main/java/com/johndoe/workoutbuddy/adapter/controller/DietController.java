package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.diet.DietFacade;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
class DietController {

    private final DietFacade dietFacade;
    private final ResponseResolver responseResolver;

    @PostMapping("/updateDaily")
    ResponseEntity updateDailyConsumption(@RequestBody DailyConsumptionDto productsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return responseResolver.resolve(dietFacade.updateDailyConsumption(username, productsDto));
    }

    @GetMapping("/getDaily")
    ResponseEntity getDailyConsumption(@RequestParam String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return responseResolver.resolve(dietFacade.getDailyConsumption(username, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withLocale(Locale.UK))));
    }
}
