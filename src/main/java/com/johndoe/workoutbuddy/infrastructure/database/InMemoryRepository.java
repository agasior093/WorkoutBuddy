package com.johndoe.workoutbuddy.infrastructure.database;

import lombok.extern.java.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log
public abstract class InMemoryRepository<T, U extends BaseEntity> {
    private final Map<T, U> repository = new HashMap<>();

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
