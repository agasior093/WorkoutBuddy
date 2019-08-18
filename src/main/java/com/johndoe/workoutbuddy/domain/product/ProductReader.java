package com.johndoe.workoutbuddy.domain.product;

import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;
import com.johndoe.workoutbuddy.domain.product.port.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class ProductReader {
    private final ProductRepository repository;

    List<ProductDto> getProducts() {
        return repository.getProducts();
    }
}
