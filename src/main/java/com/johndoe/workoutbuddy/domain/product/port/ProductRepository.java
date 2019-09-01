package com.johndoe.workoutbuddy.domain.product.port;

import com.johndoe.workoutbuddy.domain.product.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductRepository {
    void addProducts(List<Product> products);
    List<Product> getProducts();
    List<Product> getProductsByIDs(Set<String> IDs);
}
