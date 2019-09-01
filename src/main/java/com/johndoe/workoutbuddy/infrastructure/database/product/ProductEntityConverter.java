package com.johndoe.workoutbuddy.infrastructure.database.product;

import com.johndoe.workoutbuddy.domain.product.model.Product;


class ProductEntityConverter {
    Product toDto(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .calories(entity.getCalories())
                .protein(entity.getProtein())
                .fat(entity.getFat())
                .carbohydrates(entity.getCarbohydrates())
                .weight(entity.getWeight())
                .build();
    }

    ProductEntity toEntity(Product dto) {
        var product = ProductEntity.builder()
                .productName(dto.getProductName())
                .calories(dto.getCalories())
                .protein(dto.getProtein())
                .fat(dto.getFat())
                .carbohydrates(dto.getCarbohydrates())
                .weight(dto.getWeight())
                .build();
        product.setId(dto.getId());
        return product;
    }
}
