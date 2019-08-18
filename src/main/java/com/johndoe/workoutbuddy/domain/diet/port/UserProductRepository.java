package com.johndoe.workoutbuddy.domain.diet.port;

import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductsDto;

import java.time.LocalDate;
import java.util.Optional;

public interface UserProductRepository {
    void saveUserProducts(String username, ConsumedProductsDto productsDto);
    Optional<ConsumedProductsDto> getUserProducts(String username, LocalDate date);
}
