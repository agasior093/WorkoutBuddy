package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.infrastructure.repository.inmemory.InMemoryDietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class DietTest {

    private ProductFacade productFacade = mock(ProductFacade.class);
    private DietFacade dietFacade = new DietFacade(new InMemoryDietRepository(), productFacade);

    @Test
    public void shouldReturnEmptyList() {
        assertThat(dietFacade.getDailyConsumption("test", LocalDate.now()).size(), is(0));
    }
}
