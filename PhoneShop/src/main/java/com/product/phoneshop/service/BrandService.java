package com.product.phoneshop.service;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.dto.BrandDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    Brand save(BrandDTO entity);
    Brand getById(Integer id);
    List<Brand> getAllBrand();
    Brand update(Integer id, BrandDTO dto);
    void delete(Integer id);
}
