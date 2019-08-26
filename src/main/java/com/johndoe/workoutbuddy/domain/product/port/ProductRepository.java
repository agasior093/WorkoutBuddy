package com.johndoe.workoutbuddy.domain.product.port;

import com.johndoe.workoutbuddy.domain.product.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
}
