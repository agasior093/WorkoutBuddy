package com.johndoe.workoutbuddy.infrastructure.controller;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.DietFacade;
import com.johndoe.workoutbuddy.domain.diet.dto.UpdateDailyConsumptionDto;
import com.johndoe.workoutbuddy.infrastructure.controller.utils.AuthenticationHelper;
import com.johndoe.workoutbuddy.infrastructure.controller.utils.ResponseResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
class DietController {

    private final DietFacade dietFacade;
    private final ResponseResolver responseResolver;
    private final AuthenticationHelper authHelper;

    @PostMapping("/addDaily")
    ResponseEntity addToDaily(@RequestBody UpdateDailyConsumptionDto updateDailyConsumption) {
        var request = buildWithUsername(updateDailyConsumption);
        return responseResolver.resolve(dietFacade.addProductToDailyConsumption(request));
    }

    @PostMapping("/removeDaily")
    ResponseEntity updateDailyConsumption(@RequestBody UpdateDailyConsumptionDto updateDailyConsumption) {
        var request = buildWithUsername(updateDailyConsumption);
        return responseResolver.resolve(dietFacade.removeProductFromDailyConsumption(request));
    }

    @GetMapping("/daily")
    ResponseEntity getDailyConsumption(@RequestParam String date) {
        return responseResolver.resolve(dietFacade.getDailyConsumption(authHelper.getLoggedUserName(), DateUtils.fromString(date)));
    }

    @GetMapping("/weekly")
    ResponseEntity getWeeklyConsumption() {
        return responseResolver.resolve(dietFacade.getWeeklyConsumption(authHelper.getLoggedUserName()));
    }

    private UpdateDailyConsumptionDto buildWithUsername(UpdateDailyConsumptionDto dto) {
        return UpdateDailyConsumptionDto.builder()
                .date(dto.getDate())
                .username(authHelper.getLoggedUserName())
                .product(dto.getProduct())
                .build();
    }
}
