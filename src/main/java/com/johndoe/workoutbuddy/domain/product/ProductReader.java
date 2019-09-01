package com.johndoe.workoutbuddy.domain.product;

import com.johndoe.workoutbuddy.domain.product.model.Product;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class ProductReader {
    private final ProductRepository repository;

    List<Product> getProducts() {
        return repository.getProducts();
    }

    List<Product> getProducts(String... IDs) {
        return repository.getProducts(IDs);
    }
}
