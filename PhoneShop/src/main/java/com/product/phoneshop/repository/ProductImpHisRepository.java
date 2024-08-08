package com.product.phoneshop.repository;

import com.product.phoneshop.model.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImpHisRepository extends JpaRepository<ProductImportHistory, Long> , JpaSpecificationExecutor<ProductImportHistory> {

}
