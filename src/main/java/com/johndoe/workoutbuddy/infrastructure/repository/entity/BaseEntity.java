package com.johndoe.workoutbuddy.infrastructure.repository.entity;

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
