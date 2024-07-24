package com.product.phoneshop.service;

import com.product.phoneshop.dto.ProductImportDTO;
import com.product.phoneshop.model.Product;

public interface ProductService {
    Product save(ProductImportDTO productImportDto);
    Product getById(Long id);
}
