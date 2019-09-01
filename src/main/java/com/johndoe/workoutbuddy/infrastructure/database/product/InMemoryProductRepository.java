package com.johndoe.workoutbuddy.infrastructure.database.product;

import com.johndoe.workoutbuddy.domain.product.model.Product;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import com.johndoe.workoutbuddy.infrastructure.database.InMemoryRepository;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Profile("inmemory")
public class InMemoryProductRepository extends InMemoryRepository<String, ProductEntity> implements ProductRepository {

    private ProductEntityConverter converter = new ProductEntityConverter();

    public void initializeWith(List<Product> products) {
        products.stream().map(converter::toEntity).forEach(entity -> repository.put(entity.getId(), entity));
    }

    @Override
    public List<Product> getProducts() {
        return findAll().stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    protected String generateID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<Product> getProductsByIDs(Set<String> IDs) {
        return getProducts().stream().filter(p -> IDs.contains(p.getId())).collect(Collectors.toList());
    }

    @Override
    public void addProducts(List<Product> products) {
        products.forEach(p -> save(converter.toEntity(p)));
    }
}
