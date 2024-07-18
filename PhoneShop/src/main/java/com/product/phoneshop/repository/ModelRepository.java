package com.product.phoneshop.repository;

import com.product.phoneshop.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}