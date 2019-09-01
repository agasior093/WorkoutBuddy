package com.johndoe.workoutbuddy.infrastructure.database;

import com.johndoe.workoutbuddy.domain.product.model.Product;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Log
@NoArgsConstructor
public abstract class InMemoryRepository<T, U extends BaseEntity> {
    protected final Map<T, U> repository = new ConcurrentHashMap<>();

    protected abstract T generateID();

    protected U save(U object) {
        final var id = generateID();
        object.setId(id);
        repository.put(id, object);
        return repository.get(id);
    }

    protected U update(U object) {
        repository.put((T)object.getId(), object);
        return repository.get(object.getId());
    }

    protected Optional<U> findByID(T ID) {
        return Optional.ofNullable(repository.get(ID));
    }

    protected Collection<U> findAll() {
        return repository.values();
    }

    protected void remove(U object) {
        repository.remove(object.getId());
    }

    protected void remove(T ID) {
        repository.remove(ID);
    }
}
