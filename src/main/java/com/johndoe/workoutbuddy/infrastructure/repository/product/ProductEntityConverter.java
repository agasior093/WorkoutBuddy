package com.johndoe.workoutbuddy.infrastructure.repository.product;

import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;

class ProductEntityConverter {

    ProductDto toDto(ProductEntity entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .calories(entity.getCalories())
                .protein(entity.getProtein())
                .fat(entity.getFat())
                .carbohydrates(entity.getCarbohydrates())
                .weight(entity.getWeight())
                .build();
    }

    ProductEntity toEntity(ProductDto dto) {
        var product = ProductEntity.builder()
                .productName(dto.getProductName())
                .calories(dto.getCalories())
                .protein(dto.getProtein())
                .fat(dto.getFat())
                .carbohydrates(dto.getCarbohydrates())
                .build();
        product.setId(dto.getId());
        return product;
    }
}
