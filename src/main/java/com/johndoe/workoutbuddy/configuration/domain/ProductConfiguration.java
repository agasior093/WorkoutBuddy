package com.johndoe.workoutbuddy.configuration.domain;

import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {
    @Bean
    ProductFacade productFacade(ProductRepository repository) {
        return new ProductFacade(repository);
    }
}
