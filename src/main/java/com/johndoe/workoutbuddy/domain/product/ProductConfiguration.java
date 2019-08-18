package com.johndoe.workoutbuddy.domain.product;

import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductConfiguration {
    @Bean
    ProductFacade productFacade(ProductRepository repository) {
        return new ProductFacade(repository);
    }
}
