package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.diet.DietFacade;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
class DietController {

    private final DietFacade dietFacade;
    private final ResponseResolver responseResolver;

    @PostMapping("/updateDaily")
    ResponseEntity updateDailyConsumption(@RequestBody ConsumedProductsDto productsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return responseResolver.resolve(dietFacade.updateDailyConsumption(username, productsDto));
    }
}
