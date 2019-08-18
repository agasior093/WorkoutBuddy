package com.johndoe.workoutbuddy.domain.product.port;

import com.johndoe.workoutbuddy.domain.product.dto.ProductDto;

import java.util.List;

public interface ProductRepository {
    List<ProductDto> getProducts();
}
