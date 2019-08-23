package com.johndoe.workoutbuddy.infrastructure.repository.entity;

import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.domain.diet.dto.DailyConsumptionDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DietEntityConverter {

   public DailyConsumptionEntity toEntity(DailyConsumptionDto dto) {
        return DailyConsumptionEntity.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .date(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .consumedProducts(consumedProductsToEntity(dto.getConsumedProducts()))
                .build();
    }

    List<ConsumedProductEntity> consumedProductsToEntity(List<ConsumedProductDto> dtos) {
        List<ConsumedProductEntity> entities = new ArrayList<>();
        if(dtos != null) {
            entities = dtos.stream().map(this::consumedProductToEntity).collect(Collectors.toList());
        }
        return entities;
    }


    ConsumedProductEntity consumedProductToEntity(ConsumedProductDto dto) {
        return ConsumedProductEntity.builder()
                .productID(dto.getProductID())
                .weight(dto.getWeight())
                .build();
    }

    public DailyConsumptionDto toDto(DailyConsumptionEntity entity, String username) {
        return DailyConsumptionDto.builder()
                .id(entity.getId())
                .username(username)
                .date(entity.getDate().toString())
                .consumedProducts(consumedProductsToDto(entity.getConsumedProducts()))
                .build();
    }

    List<ConsumedProductDto> consumedProductsToDto(List<ConsumedProductEntity> entities) {
        List<ConsumedProductDto> dtos = new ArrayList<>();
        if(entities != null) {
            dtos = entities.stream().map(this::consumedProductToDto).collect(Collectors.toList());
        }
        return dtos;
    }

    ConsumedProductDto consumedProductToDto(ConsumedProductEntity entity) {
        return ConsumedProductDto.builder()
                .productID(entity.getProductID())
                .weight(entity.getWeight())
                .build();
    }
}
