package com.johndoe.workoutbuddy.adapter.repository.entity;

import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;

public class ProductEntityConverter {

    public ProductDto toDto(ProductEntity entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .calories(entity.getCalories())
                .protein(entity.getProtein())
                .fat(entity.getFat())
                .carbohydrates(entity.getCarbohydrates())
                .weight(100d)
                .build();
    }


    public ProductEntity toEntity(ProductDto dto) {
        return ProductEntity.builder()
                .id(dto.getId())
                .productName(dto.getProductName())
                .calories(dto.getCalories())
                .protein(dto.getProtein())
                .fat(dto.getFat())
                .carbohydrates(dto.getCarbohydrates())
                .build();
    }
}
