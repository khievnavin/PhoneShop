package com.product.phoneshop.service;

import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.dto.BrandDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BrandService {
    Brand save(BrandDTO entity) throws ServiceException;
    Brand getById(Integer id) throws ServiceException;
    List<Brand> getAllBrand();
    Brand update(Integer id, BrandDTO dto) throws ServiceException ;
    void delete(Integer id) throws ServiceException ;
}
