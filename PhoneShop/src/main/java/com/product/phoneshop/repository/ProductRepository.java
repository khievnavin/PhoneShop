package com.product.phoneshop.repository;

import com.product.phoneshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    //model , color
    Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
}
