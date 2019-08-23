package com.johndoe.workoutbuddy.infrastructure.database;

import org.springframework.data.annotation.Id;

public class BaseEntity<T> {
    @Id
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
