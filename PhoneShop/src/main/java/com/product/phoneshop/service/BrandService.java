package com.product.phoneshop.service;

import com.product.phoneshop.model.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface BrandService {
    Brand save(Brand entity);
    Brand getById(Long id) ;
    Brand update(Long id, Brand brand);
    void delete(Long id);
    List<Brand> getAllBrand();
}
