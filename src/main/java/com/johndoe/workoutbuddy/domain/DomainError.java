package com.johndoe.workoutbuddy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface DomainError {
    String getCause();
}
