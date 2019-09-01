package com.johndoe.workoutbuddy.infrastructure.database.product;

import com.johndoe.workoutbuddy.domain.product.model.Product;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Profile("mongo")
public class MongoProductRepository implements ProductRepository {

    private final MongoTemplate mongoTemplate;
    private final ProductEntityConverter converter = new ProductEntityConverter();

    @Override
    public List<Product> getProducts() {
        return mongoTemplate.findAll(ProductEntity.class).stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByIDs(Set<String> IDs) {
        return null;
    }

    @Override
    public void addProducts(List<Product> products) {

    }
}
