package com.johndoe.workoutbuddy.infrastructure.repository.diet;

import com.johndoe.workoutbuddy.infrastructure.repository.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Document(collection = "dailyConsumption")
class DailyConsumptionEntity extends BaseEntity<String> {
    private final String username;
    private final LocalDate date;
    private final List<ConsumedProductEntity> consumedProducts;
    private final Double calories;
    private final Double protein;
    private final Double fat;
    private final Double carbohydrates;
}
