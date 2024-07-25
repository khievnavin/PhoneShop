package com.product.phoneshop.service;

import com.product.phoneshop.dto.ProductDisplayDTO;
import com.product.phoneshop.dto.ProductImportDTO;
import com.product.phoneshop.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product save(ProductImportDTO productImportDto);
    Product getById(Long id);
    Product setPrice(Long productId, Double price);
    Page<Product> getProducts(Map<String,String> params);
    List<ProductDisplayDTO> toProductDisplayDTO(List<Product> products);
}
