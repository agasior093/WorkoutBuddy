package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.dto.ConsumedProductDto;
import com.johndoe.workoutbuddy.infrastructure.database.diet.InMemoryDietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
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
        var consumedProduct = rice(100d);
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).isEmpty());
        //when
        var result = dietFacade.addProductToDailyConsumption(consumedProduct);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(1));
        assertTrue(result.get().getConsumedProducts().contains(consumedProduct));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        getProductById(consumedProduct.getProductID()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldAddProductToExistingDailyConsumption() {
        //given
        shouldCreateNewDailyConsumption();
        var consumedProduct = chicken(100d);
        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        //when
        var result = dietFacade.addProductToDailyConsumption(consumedProduct);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(2));
        assertTrue(result.get().getConsumedProducts().contains(consumedProduct));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        getProductById(consumedProduct.getProductID()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldRemoveProductFromExistingDaily() {
        //given
        shouldAddProductToExistingDailyConsumption();
        var removedProduct = chicken(100d);
        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        //when
        var result = dietFacade.removeProductFromDailyConsumption(removedProduct);
        //then
        assertTrue(result.isRight());
        assertFalse(result.get().getConsumedProducts().contains(removedProduct));

        assertThat(result.get().getConsumedProducts().size(), is(1));
        getProductById(removedProduct.getProductID()).ifPresentOrElse(product ->
                        assertFalse(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldAddTwoEqualProducts() {
        //given
        var consumedProduct = salmon(100d);
        //when
        dietFacade.addProductToDailyConsumption(consumedProduct);
        var result = dietFacade.addProductToDailyConsumption(consumedProduct);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(2));
        assertTrue(result.get().getConsumedProducts().contains(consumedProduct));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        getProductById(consumedProduct.getProductID()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }

    @Test
    public void shouldRemoveOnlyOneProduct() {
        //given
        shouldAddTwoEqualProducts();
        var removedProduct = salmon(100d);
        //when
        var result = dietFacade.removeProductFromDailyConsumption(removedProduct);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(1));
        assertTrue(result.get().getConsumedProducts().contains(removedProduct));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        getProductById(removedProduct.getProductID()).ifPresentOrElse(product ->
                        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(product)),
                Assert::fail
        );
    }
}

