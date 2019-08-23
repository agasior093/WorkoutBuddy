package com.johndoe.workoutbuddy.infrastructure.database.diet;

import com.johndoe.workoutbuddy.common.converters.ListConverter;
import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;

class DietEntityConverter {

   DailyConsumptionEntity toEntity(DailyConsumptionDto dto) {

       var entity = DailyConsumptionEntity.builder()
               .username(dto.getUsername())
               .date(DateUtils.fromString(dto.getDate()))
               .consumedProducts(new ListConverter<ConsumedProductEntity, ConsumedProductDto>
                       (this::productToEntity).apply(dto.getConsumedProducts()))
               .build();
        entity.setId(dto.getId());
        return entity;
    }

    ConsumedProductEntity productToEntity(ConsumedProductDto dto) {
        return ConsumedProductEntity.builder()
                .productID(dto.getProductID())
                .weight(dto.getWeight())
                .build();
    }

    DailyConsumptionDto toDto(DailyConsumptionEntity entity, String username) {
        return DailyConsumptionDto.builder()
                .id(entity.getId())
                .username(username)
                .date(entity.getDate().toString())
                .consumedProducts(new ListConverter<ConsumedProductDto, ConsumedProductEntity>
                        (this::productToDto).apply(entity.getConsumedProducts()))
                .build();
    }

    ConsumedProductDto productToDto(ConsumedProductEntity entity) {
        return ConsumedProductDto.builder()
                .productID(entity.getProductID())
                .weight(entity.getWeight())
                .build();
    }
}
