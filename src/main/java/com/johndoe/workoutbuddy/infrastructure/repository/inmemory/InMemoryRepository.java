package com.johndoe.workoutbuddy.infrastructure.repository.inmemory;

import com.johndoe.workoutbuddy.infrastructure.repository.entity.BaseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryRepository<T, U extends BaseEntity> {
    private final Map<T, U> repository = new HashMap<>();

    protected abstract T generateID();

    public void save(U object) {
        repository.put(generateID(), object);
    }

    public void update(U object) {
        repository.put((T)object.getId(), object);
    }

    public Optional<U> findByID(T ID) {
        return Optional.ofNullable(repository.get(ID));
    }

    public Collection<U> findAll() {
        return repository.values();
    }

    public void remove(U object) {
        repository.remove(object.getId());
    }

    public void remove(T ID) {
        repository.remove(ID);
    }

}
