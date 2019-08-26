package com.johndoe.workoutbuddy.domain.product;

import com.johndoe.workoutbuddy.domain.product.model.Product;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;

import java.util.List;

public class ProductFacade {

    private final ProductRepository repository;
    private final ProductReader productReader;

    ProductFacade(ProductRepository repository) {
        this.repository = repository;
        this.productReader = new ProductReader(this.repository);
    }

    public List<Product> getProducts() {
        return productReader.getProducts();
    }


}
