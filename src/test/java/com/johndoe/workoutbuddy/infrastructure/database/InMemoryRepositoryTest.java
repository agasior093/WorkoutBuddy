package com.johndoe.workoutbuddy.infrastructure.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryRepositoryTest  {

    private InMemoryRepository<String, TestEntity> repository;

    @Before
    public void initialize() {
        repository = new InMemoryRepository<String, TestEntity>() {
            @Override
            protected String generateID() {
                return UUID.randomUUID().toString();
            }
        };
    }

    @Test
    public void shouldSaveObject() {
        //given
        var testValue = "hello world";
        //when
        var savedEntity = repository.save(new TestEntity(testValue));
        var foundEntity = repository.findByID(savedEntity.getId());
        //then
        assertNotNull(savedEntity.getId());
        assertThat(savedEntity.getValue(), is(testValue));
        assertTrue(foundEntity.isPresent());
        assertEquals(savedEntity, foundEntity.get());
    }

    @Test
    public void shouldRemoveObject() {
        //given
        var testValue = "hello world";
        var entity = repository.save(new TestEntity(testValue));
        //when
        repository.remove(entity);
        //then
        assertFalse(repository.findByID(entity.getId()).isPresent());
    }

    @Test
    public void shouldRemoveObjectById() {
        //given
        var testValue = "hello world";
        var entity = repository.save(new TestEntity(testValue));
        //when
        repository.remove(entity.getId());
        //then
        assertFalse(repository.findByID(entity.getId()).isPresent());
    }

    @Test
    public void shouldFindAllObjects() {
        //given
        final var testValue1 = "hello world";
        final var testValue2 = "lorem ipsum";
        var entity1 = repository.save(new TestEntity(testValue1));
        var entity2 = repository.save(new TestEntity(testValue2));
        //when
        var result = repository.findAll();
        //then
        assertThat(result.size(), is(2));
        assertTrue(result.containsAll(List.of(entity1, entity2)));
    }
}
