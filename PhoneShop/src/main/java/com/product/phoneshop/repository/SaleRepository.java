package com.product.phoneshop.repository;

import com.product.phoneshop.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleRepository extends JpaRepository<Sale, Long> {
}
