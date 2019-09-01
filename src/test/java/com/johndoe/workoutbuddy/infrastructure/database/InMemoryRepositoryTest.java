package com.johndoe.workoutbuddy.infrastructure.database;

import lombok.extern.java.Log;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Log
@RunWith(MockitoJUnitRunner.class)
public class InMemoryRepositoryTest extends InMemoryRepository<String, TestEntity> {
    @Override
    protected String generateID() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void shouldUpdateObject() {
        var entity = save(new TestEntity("hello world"));
        for(int i = 0; i < 1000; i++) {
            var updateEntity = new TestEntity(entity.getValue() + i);
            updateEntity.setId(entity.getId());
            update(updateEntity);
            assertThat(findAll().size(), is(1));
        }
    }
}
