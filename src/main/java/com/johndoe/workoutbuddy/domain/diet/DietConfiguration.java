package com.johndoe.workoutbuddy.domain.diet;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DietConfiguration {

    @Bean
    DietFacade dietFacade() {
        return new DietFacade(new DailyConsumptionCreator());
    }
}
