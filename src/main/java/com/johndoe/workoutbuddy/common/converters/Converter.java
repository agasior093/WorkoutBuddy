package com.johndoe.workoutbuddy.common.converters;

@FunctionalInterface
public interface Converter<T, U> {
    T convert(U obj);
}
