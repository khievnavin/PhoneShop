package com.product.phoneshop.repository;

import com.product.phoneshop.model.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImpHisRepository extends JpaRepository<ProductImportHistory, Long> {

}
