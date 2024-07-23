package com.product.phoneshop.service.impl;

import com.product.phoneshop.dto.ProductDTO;
import com.product.phoneshop.mapper.ProductMapper;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.repository.ProductRepository;
import com.product.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public Product save(ProductDTO productDto) {
        return productRepository.save(productMapper.toProduct(productDto));
    }
}
