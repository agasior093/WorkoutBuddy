package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.dto.UpdateDailyConsumptionDto;
import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import com.johndoe.workoutbuddy.domain.product.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class ObjectFactory {
    static final String USERNAME_1 = "user1";

    static List<Product> products() {
        return List.of(riceProduct(100d), chickenProduct(100d), appleProduct(100d), salmonProduct(100d));
    }

    static Product getProductById(String id) {
        return products().stream().filter(p -> id.equals(p.getId())).findFirst().orElse(Product.builder().build());
    }

    static Product riceProduct(Double weight) {
        return Product.builder().id("1").productName("rice").weight(weight).calories(350d).protein(2d).fat(0d).carbohydrates(70d).build();
    }

    static Product chickenProduct(Double weight) {
        return Product.builder().id("2").productName("chicken").weight(weight).calories(120d).protein(20d).fat(5d).carbohydrates(4d).build();
    }

    static Product appleProduct(Double weight) {
        return Product.builder().id("3").productName("apple").weight(weight).calories(20d).protein(0d).fat(0d).carbohydrates(5d).build();
    }

    static Product salmonProduct(Double weight) {
        return Product.builder().id("4").productName("salmon").weight(weight).calories(300d).protein(18d).fat(18d).carbohydrates(4d).build();
    }

    static UpdateDailyConsumptionDto updateWithRice(Double weight) {
        var consumedProduct = ConsumedProduct.builder().id("1").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(DateUtils.today()).username(USERNAME_1).build();
    }

    static UpdateDailyConsumptionDto updateWithChicken(Double weight) {
        var consumedProduct = ConsumedProduct.builder().id("2").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(DateUtils.today()).username(USERNAME_1).build();
    }

    static UpdateDailyConsumptionDto updateWithApple(Double weight) {
        var consumedProduct = ConsumedProduct.builder().id("3").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(DateUtils.today()).username(USERNAME_1).build();
    }

    static UpdateDailyConsumptionDto updateWithSalmon(Double weight) {
        var consumedProduct = ConsumedProduct.builder().id("4").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(DateUtils.today()).username(USERNAME_1).build();
    }

    static UpdateDailyConsumptionDto updateWithRice(Double weight, LocalDate date, String username) {
        var consumedProduct = ConsumedProduct.builder().id("1").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(date).username(username).build();
    }

    static UpdateDailyConsumptionDto updateWithChicken(Double weight, LocalDate date, String username) {
        var consumedProduct = ConsumedProduct.builder().id("2").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(date).username(username).build();
    }

    static UpdateDailyConsumptionDto updateWithApple(Double weight, LocalDate date, String username) {
        var consumedProduct = ConsumedProduct.builder().id("3").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(date).username(username).build();
    }

    static UpdateDailyConsumptionDto updateWithSalmon(Double weight, LocalDate date, String username) {
        var consumedProduct = ConsumedProduct.builder().id("4").weight(weight).build();
        return UpdateDailyConsumptionDto.builder().product(consumedProduct).date(date).username(username).build();
    }

}
