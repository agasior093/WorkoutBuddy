package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.converters.ListConverter;
import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;

class DietConverter {
    DailyConsumption toEntity(DailyConsumptionDto dto) {
        return DailyConsumption.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .date(DateUtils.fromString(dto.getDate()))
                .consumedProducts(new ListConverter<ConsumedProduct, ConsumedProductDto>(this::toConsumedProductEntity).apply(dto.getConsumedProducts()))
                .build();
    }

    DailyConsumptionDto toDto(DailyConsumption entity) {
        return DailyConsumptionDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .date(DateUtils.toString(entity.getDate()))
                .consumedProducts(new ListConverter<ConsumedProductDto, ConsumedProduct>(this::toConsumedProductDto).apply(entity.getConsumedProducts()))
                .build();
    }

    ConsumedProduct toConsumedProductEntity(ConsumedProductDto dto) {
        return new ConsumedProduct(dto.getProductID(), dto.getWeight());
    }

    ConsumedProductDto toConsumedProductDto(ConsumedProduct entity) {
        return ConsumedProductDto.builder().productID(entity.getProductID()).weight(entity.getWeight()).build();
    }
}
