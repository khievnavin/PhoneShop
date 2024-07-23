package com.product.phoneshop.controller;

import com.product.phoneshop.dto.ProductDTO;
import com.product.phoneshop.mapper.ProductMapper;
import com.product.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productMapper.toDTO(productService.save(dto)));
    }
}
