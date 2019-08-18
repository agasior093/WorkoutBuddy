package com.johndoe.workoutbuddy.domain.diet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@ToString
public class ConsumedProductsDto {
    private final String date;
    private final List<ProductDto> products;
}
