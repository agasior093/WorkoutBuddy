package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.infrastructure.database.diet.InMemoryDietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.johndoe.workoutbuddy.domain.diet.ObjectFactory.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
@Log
public class DietTest {

    private ProductFacade productFacade = mock(ProductFacade.class);
    private InMemoryDietRepository repository = new InMemoryDietRepository();
    private DietFacade dietFacade = new DietFacade(repository, productFacade);

    @Before
    public void setUp() {
        when(productFacade.getProducts()).thenReturn(products());
        this.repository = new InMemoryDietRepository();
    }

    @Test
    public void shouldCreateNewDailyConsumption() {
        //given
        var updateDto = rice(100d);
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).isEmpty());
        //when
        var result = dietFacade.addProductToDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(1));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        getProductById(updateDto.getProduct().getId()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldAddProductToExistingDailyConsumption() {
        //given
        shouldCreateNewDailyConsumption();
        var updateDto = chicken(100d);
        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        //when
        var result = dietFacade.addProductToDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(2));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        getProductById(updateDto.getProduct().getId()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldRemoveProductFromExistingDaily() {
        //given
        shouldAddProductToExistingDailyConsumption();
        var updateDto = chicken(100d);
        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        //when
        var result = dietFacade.removeProductFromDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertFalse(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(result.get().getConsumedProducts().size(), is(1));
        getProductById(updateDto.getProduct().getId()).ifPresentOrElse(product ->
                        assertFalse(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldAddTwoEqualProducts() {
        //given
        var updateDto = salmon(100d);
        //when
        dietFacade.addProductToDailyConsumption(updateDto);
        var result = dietFacade.addProductToDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(2));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        getProductById(updateDto.getProduct().getId()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldRemoveOnlyOneProduct() {
        //given
        shouldAddTwoEqualProducts();
        var updateDto = salmon(100d);
        //when
        var result = dietFacade.removeProductFromDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(1));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        getProductById(updateDto.getProduct().getId()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }
}

