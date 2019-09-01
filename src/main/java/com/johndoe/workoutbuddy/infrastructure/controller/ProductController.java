package com.johndoe.workoutbuddy.infrastructure.controller;

import com.johndoe.workoutbuddy.domain.product.ProductFacade;
import com.johndoe.workoutbuddy.infrastructure.controller.utils.ResponseResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
class ProductController {
   private final ProductFacade productFacade;
   private final ResponseResolver responseResolver;

    @GetMapping("/all")
    ResponseEntity getAllProducts() {
        return responseResolver.resolve(productFacade.getProducts());
    }
}
