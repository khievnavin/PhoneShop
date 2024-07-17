package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.dto.BrandDTO;

public class EntityMapper {
    public static Brand toBrand(BrandDTO dto){
        Brand brand = new Brand();
        brand.setName(dto.getName());
    return brand;
    }
    public static BrandDTO toBrandDTO(Brand entity){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(entity.getName());
        return brandDTO;
    }
}
