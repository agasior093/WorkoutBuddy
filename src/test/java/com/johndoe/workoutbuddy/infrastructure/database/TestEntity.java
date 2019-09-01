package com.johndoe.workoutbuddy.infrastructure.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
class TestEntity extends BaseEntity<String> {
    private String value;
}
