package com.product.phoneshop.repository;

import com.product.phoneshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
    List<Brand> findByIdIn(List<Brand> ids);
  //  List<Brand> findByActive(boolean isActive);
    List<Brand> findByActiveTrue();
}