package com.johndoe.workoutbuddy.domain.diet;

import com.johndoe.workoutbuddy.common.utils.DateUtils;
import com.johndoe.workoutbuddy.domain.diet.model.ConsumedProduct;
import com.johndoe.workoutbuddy.domain.diet.model.DailyConsumption;
import com.johndoe.workoutbuddy.domain.diet.model.PeriodConsumption;
import com.johndoe.workoutbuddy.domain.diet.port.DietRepository;
import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.domain.product.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class PeriodConsumptionReader {
    private final DietRepository repository;
    private final ProductFacade productFacade;

    Optional<PeriodConsumption> getWeeklyConsumption(final String username) {
        final var weeklyConsumption = repository.getConsumptionFromDate(username, DateUtils.today().minusDays(6));
        if (weeklyConsumption.isEmpty()) return Optional.empty();
        final var consumedProducts = weeklyConsumption.stream().map(DailyConsumption::getConsumedProducts).collect(Collectors.toList());
        final var products = findProducts(consumedProducts);
        return build(matchProducts(consumedProducts, products));
    }

    private List<Product> findProducts(List<List<ConsumedProduct>> consumedProductsList) {
        final var productIds = consumedProductsList.stream().flatMap(List::stream).map(ConsumedProduct::getId).collect(Collectors.toSet());
        return productFacade.getProductsByIDs(productIds);
    }

    private List<List<Product>> matchProducts(List<List<ConsumedProduct>> consumedProducts, List<Product> products) {
        return consumedProducts.stream().map(list -> matchDailyProducts(list, products)).collect(Collectors.toList());
    }

    private List<Product> matchDailyProducts(List<ConsumedProduct> consumedProducts, List<Product> products) {
        return consumedProducts.stream().map(p -> toProduct(p, products)).map(Optional::get).collect(Collectors.toList());
    }

    private Optional<PeriodConsumption> build(List<List<Product>> products) {
        if (products.isEmpty()) return Optional.empty();
        return Optional.of(PeriodConsumption.builder()
                .calories(calculateCalories(products))
                .protein(calculateProtein(products))
                .fat(calculateFat(products))
                .carbohydrates(calculateCarbohydrates(products))
                .products(products)
                .build());
    }

    private Optional<Product> toProduct(ConsumedProduct consumedProduct, List<Product> products) {
        return products.stream().filter(p -> p.getId().equals(consumedProduct.getId())).findFirst();
    }

    private Double calculateCalories(List<List<Product>> products) {
        return products.stream().flatMap(List::stream).collect(Collectors.toList())
                .stream().mapToDouble(Product::getCalories).sum();
    }

    private Double calculateProtein(List<List<Product>> products) {
        return products.stream().flatMap(List::stream).collect(Collectors.toList())
                .stream().mapToDouble(Product::getProtein).sum();
    }

    private Double calculateFat(List<List<Product>> products) {
        return products.stream().flatMap(List::stream).collect(Collectors.toList())
                .stream().mapToDouble(Product::getFat).sum();
    }

    private Double calculateCarbohydrates(List<List<Product>> products) {
        return products.stream().flatMap(List::stream).collect(Collectors.toList())
                .stream().mapToDouble(Product::getCarbohydrates).sum();
    }
}
