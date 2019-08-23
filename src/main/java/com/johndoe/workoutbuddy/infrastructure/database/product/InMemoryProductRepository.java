package com.johndoe.workoutbuddy.infrastructure.database.product;

import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import com.johndoe.workoutbuddy.infrastructure.database.InMemoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Profile("inmemory")
public class InMemoryProductRepository extends InMemoryRepository<String, ProductEntity> implements ProductRepository {

    private final ProductEntityConverter converter = new ProductEntityConverter();

    @Override
    public List<ProductDto> getProducts() {
        return findAll().stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    protected String generateID() {
        return UUID.randomUUID().toString();
    }
}
