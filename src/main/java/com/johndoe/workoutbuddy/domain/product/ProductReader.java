package com.johndoe.workoutbuddy.domain.product;

import com.johndoe.workoutbuddy.domain.product.model.Product;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
class ProductReader {
    private final ProductRepository repository;

    List<Product> getProducts() {
        return repository.getProducts();
    }

    List<Product> getProducts(Set<String> IDs) {
        return repository.getProductsByIDs(IDs);
    }
}
