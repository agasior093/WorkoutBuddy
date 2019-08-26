package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;

import java.util.List;
import java.util.Optional;

class ObjectFactory {
    static final String USERNAME_1 = "user1";
    static final String USERNAME_2 = "user2";

    static List<ProductDto> products() {
        return List.of(riceProduct(100d), chickenProduct(100d), appleProduct(100d), salmonProduct(100d));
    }

    static Optional<ProductDto> getProductById(String id) {
        return products().stream().filter(p -> id.equals(p.getId())).findFirst();
    }

    static ProductDto riceProduct(Double weight) {
        return ProductDto.builder().id("1").productName("rice").weight(weight).calories(350d).protein(2d).fat(0d).carbohydrates(70d).build();
    }

    static ProductDto chickenProduct(Double weight) {
        return ProductDto.builder().id("2").productName("chicken").weight(weight).calories(120d).protein(20d).fat(5d).carbohydrates(4d).build();
    }

    static ProductDto appleProduct(Double weight) {
        return ProductDto.builder().id("3").productName("apple").weight(weight).calories(20d).protein(0d).fat(0d).carbohydrates(5d).build();
    }

    static ProductDto salmonProduct(Double weight) {
        return ProductDto.builder().id("4").productName("salmon").weight(weight).calories(300d).protein(18d).fat(18d).carbohydrates(4d).build();
    }

    static ConsumedProductDto rice(Double weight) {
        return ConsumedProductDto.builder().productID("1").weight(weight).build();
    }

    static ConsumedProductDto chicken(Double weight) {
        return ConsumedProductDto.builder().productID("2").weight(weight).build();
    }

    static ConsumedProductDto apple(Double weight) {
        return ConsumedProductDto.builder().productID("3").weight(weight).build();
    }

    static ConsumedProductDto salmon(Double weight) {
        return ConsumedProductDto.builder().productID("4").weight(weight).build();
    }
}
