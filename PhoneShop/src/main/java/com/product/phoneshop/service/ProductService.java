package com.product.phoneshop.service;

import com.product.phoneshop.dto.ProductDTO;
import com.product.phoneshop.model.Product;

public interface ProductService {
    Product save(ProductDTO product);
}
