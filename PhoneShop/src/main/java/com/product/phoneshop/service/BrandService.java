package com.product.phoneshop.service;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.dto.BrandDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface BrandService {
    Brand save(Brand entity);
    Brand getById(Integer id) ;
    Brand update(Integer id, Brand brand);
    void delete(Integer id);
    List<Brand> getAllBrand();
}
