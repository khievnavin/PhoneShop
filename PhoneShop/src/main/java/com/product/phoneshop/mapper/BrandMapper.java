package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.dto.BrandDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toEntity(BrandDTO dto);
    BrandDTO toDTO(Brand entity);
}
