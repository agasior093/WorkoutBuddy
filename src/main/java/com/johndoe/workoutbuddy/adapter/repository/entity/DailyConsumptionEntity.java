package com.johndoe.workoutbuddy.adapter.repository.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Document(collection = "dailyConsumption")
public class DailyConsumptionEntity {
    @Id
    private String id;
    private final String username;
    private final LocalDate date;
    private final List<ConsumedProductEntity> consumedProducts;
}
