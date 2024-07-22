package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.service.BrandService;
import com.product.phoneshop.service.dto.ModelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , uses = {BrandService.class})
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "brand" , source = "dto.brandId")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandId" , source = "brand.id")
    ModelDTO toDTO(Model entity);
}