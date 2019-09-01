package com.johndoe.workoutbuddy.infrastructure.database.diet;

import com.johndoe.workoutbuddy.infrastructure.database.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@ToString
@Document(collection = "dailyConsumption")
class DailyConsumptionEntity extends BaseEntity<String> {
    private final String username;
    private final LocalDate date;
    private final List<ConsumedProductEntity> consumedProducts;
}
