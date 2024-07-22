package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.dto.BrandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
   BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand toEntity(BrandDTO dto);
    BrandDTO toDTO(Brand entity);
    void update(@MappingTarget Brand target , Brand source);
}
