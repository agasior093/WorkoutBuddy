package com.johndoe.workoutbuddy.infrastructure.database.diet;

import com.johndoe.workoutbuddy.common.converters.ListConverter;
import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;

class DietEntityConverter {

    DailyConsumptionEntity toEntity(DailyConsumption dto) {

        var entity = DailyConsumptionEntity.builder()
                .username(dto.getUsername())
                .date(dto.getDate())
                .consumedProducts(new ListConverter<ConsumedProductEntity, ConsumedProduct>
                        (this::productToEntity).apply(dto.getConsumedProducts()))
                .build();
        entity.setId(dto.getId());
        return entity;
    }

    ConsumedProductEntity productToEntity(ConsumedProduct dto) {
        return ConsumedProductEntity.builder()
                .productID(dto.getId())
                .weight(dto.getWeight())
                .build();
    }

    DailyConsumption toDto(DailyConsumptionEntity entity) {
        return DailyConsumption.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .date(entity.getDate())
                .consumedProducts(new ListConverter<ConsumedProduct, ConsumedProductEntity>
                        (this::productToDto).apply(entity.getConsumedProducts()))
                .build();
    }

    ConsumedProduct productToDto(ConsumedProductEntity entity) {
        return new ConsumedProduct(entity.getProductID(), entity.getWeight());
    }
}
