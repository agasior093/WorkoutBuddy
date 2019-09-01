package com.johndoe.workoutbuddy.domain.product;

import com.johndoe.workoutbuddy.domain.product.model.Product;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;

import java.util.List;
import java.util.Set;

public class ProductFacade {

    private final ProductRepository repository;
    private final ProductReader productReader;

    public ProductFacade(ProductRepository repository) {
        this.repository = repository;
        this.productReader = new ProductReader(this.repository);
    }

    public List<Product> getProducts() {
        return productReader.getProducts();
    }

    public List<Product> getProductsByIDs(Set<String> IDs) {
        return productReader.getProducts(IDs);
    }

    public void addProducts(List<Product> products) {
        this.repository.addProducts(products);
    }

}
