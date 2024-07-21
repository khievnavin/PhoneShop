package com.product.phoneshop.repository;

import com.product.phoneshop.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModelRepository extends JpaRepository<Model, Integer>, JpaSpecificationExecutor<Model> {

}