package com.johndoe.workoutbuddy.common.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListConverter<T, S> implements Converter<T, S> {

    private final Converter<T, S> converter;

    public ListConverter(Converter<T, S> converter) {
        this.converter = converter;
    }

    public List<T> apply(List<S> sourceList) {
        return sourceList != null ? sourceList.stream().map(this::convert).collect(Collectors.toList()) : new ArrayList<T>();
    }

    @Override
    public T convert(S element) {
        return this.converter.convert(element);
    }
}
