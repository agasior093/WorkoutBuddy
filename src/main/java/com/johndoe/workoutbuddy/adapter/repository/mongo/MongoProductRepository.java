package com.johndoe.workoutbuddy.adapter.repository.mongo;

import com.johndoe.workoutbuddy.adapter.repository.entity.ProductEntity;
import com.johndoe.workoutbuddy.adapter.repository.entity.ProductEntityConverter;
import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoProductRepository implements ProductRepository {

    private final MongoTemplate mongoTemplate;
    private final ProductEntityConverter converter = new ProductEntityConverter();

    @Override
    public List<ProductDto> getProducts() {
        return mongoTemplate.findAll(ProductEntity.class).stream().map(converter::toDto).collect(Collectors.toList());
    }
}
