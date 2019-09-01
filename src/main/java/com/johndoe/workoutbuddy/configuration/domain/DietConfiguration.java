package com.johndoe.workoutbuddy.configuration.domain;

import com.johndoe.workoutbuddy.domain.diet.DietFacade;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DietConfiguration {

    @Bean
    DietFacade dietFacade(DietRepository repository, ProductFacade productFacade) {
        return new DietFacade(repository, productFacade);
    }
}
