package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.product.model.Product;
import com.johndoe.workoutbuddy.infrastructure.database.diet.InMemoryDietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.infrastructure.database.product.InMemoryProductRepository;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static com.johndoe.workoutbuddy.domain.diet.ObjectFactory.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
@Log
public class DietTest {
    private InMemoryProductRepository productRepository = new InMemoryProductRepository();
    private InMemoryDietRepository dietRepository = new InMemoryDietRepository();
    private ProductFacade productFacade = new ProductFacade(productRepository);
    private DietFacade dietFacade = new DietFacade(dietRepository, productFacade);

    @Before
    public void setUp() {
        this.productRepository.initializeWith(products());
        this.dietRepository = new InMemoryDietRepository();
    }

    @Test
    public void shouldCreateNewDailyConsumption() {
        //given
        var updateDto = updateWithRice(100d);
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).isEmpty());
        //when
        var result = dietFacade.addProductToDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(1));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(getProductById(updateDto.getProduct().getId())));
    }

    @Test
    public void shouldAddProductToExistingDailyConsumption() {
        //given
        dietFacade.addProductToDailyConsumption(updateWithRice(100d));
        var updateDto = updateWithChicken(100d);
        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        //when
        var result = dietFacade.addProductToDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(2));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(getProductById(updateDto.getProduct().getId())));
    }

    @Test
    public void shouldRemoveProductFromExistingDaily() {
        //given
        dietFacade.addProductToDailyConsumption(updateWithRice(100d));
        dietFacade.addProductToDailyConsumption(updateWithApple(100d));
        var updateDto = updateWithRice(100d);
        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        //when
        var result = dietFacade.removeProductFromDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertFalse(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(result.get().getConsumedProducts().size(), is(1));
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(getProductById(updateDto.getProduct().getId())));
    }

    @Test
    public void shouldAddTwoEqualProducts() {
        //given
        var updateDto = updateWithSalmon(100d);
        //when
        dietFacade.addProductToDailyConsumption(updateDto);
        var result = dietFacade.addProductToDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(2));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(2));
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(getProductById(updateDto.getProduct().getId())));
    }

    @Test
    public void shouldRemoveOnlyOneProduct() {
        //given
        dietFacade.addProductToDailyConsumption(updateWithSalmon(100d));
        dietFacade.addProductToDailyConsumption(updateWithSalmon(100d));
        var updateDto = updateWithSalmon(100d);
        //when
        var result = dietFacade.removeProductFromDailyConsumption(updateDto);
        //then
        assertTrue(result.isRight());
        assertThat(result.get().getConsumedProducts().size(), is(1));
        assertTrue(result.get().getConsumedProducts().contains(updateDto.getProduct()));

        assertThat(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).size(), is(1));
        assertTrue(dietFacade.getDailyConsumption(USERNAME_1, DateUtils.today()).contains(getProductById(updateDto.getProduct().getId())));
    }

    @Test
    public void shouldReturnWeeklyConsumptionHistory() {
        //given
        var update1 = updateWithRice(120d, DateUtils.today().minusDays(6), USERNAME_1);
        var update2 = updateWithRice(220d, DateUtils.today().minusDays(3), USERNAME_1);
        var update3 = updateWithChicken(200d, DateUtils.today().minusDays(3), USERNAME_1);
        var update4 = updateWithApple(80d, DateUtils.today(), USERNAME_1);
        var weeklyUpdates = List.of(update1, update2, update3, update4);
        for(var update : weeklyUpdates) {
            dietFacade.addProductToDailyConsumption(update);
        }
        var totalCalories = weeklyUpdates.stream().map(day -> getProductById(day.getProduct().getId())).mapToDouble(Product::getCalories).sum();
        var totalProtein =  weeklyUpdates.stream().map(day -> getProductById(day.getProduct().getId())).mapToDouble(Product::getProtein).sum();
        var totalFat =  weeklyUpdates.stream().map(day -> getProductById(day.getProduct().getId())).mapToDouble(Product::getFat).sum();
        var totalCarbohydrates =  weeklyUpdates.stream().map(day -> getProductById(day.getProduct().getId())).mapToDouble(Product::getCarbohydrates).sum();
        //when
        var result = dietFacade.getWeeklyConsumption(USERNAME_1);
        //then
        assertTrue(result.isPresent());
        assertThat(result.get().getCalories(), is(totalCalories));
        assertThat(result.get().getProtein(), is(totalProtein));
        assertThat(result.get().getFat(), is(totalFat));
        assertThat(result.get().getCarbohydrates(), is(totalCarbohydrates));
        var flatResult = result.get().getProducts().stream().flatMap(List::stream).collect(Collectors.toList());
        var products = weeklyUpdates.stream().map(p -> getProductById(p.getProduct().getId())).collect(Collectors.toList());
        assertTrue(flatResult.containsAll(products));
    }
}

