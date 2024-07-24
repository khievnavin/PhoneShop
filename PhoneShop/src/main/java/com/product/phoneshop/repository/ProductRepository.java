package com.product.phoneshop.repository;

import com.product.phoneshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //model , color
    Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
}
