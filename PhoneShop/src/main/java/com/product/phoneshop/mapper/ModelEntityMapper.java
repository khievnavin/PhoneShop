package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Model;
import com.product.phoneshop.service.BrandService;
import com.product.phoneshop.dto.ModelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
    ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);

    @Mapping(target = "brand", source = "dto.brandId")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO toDTO(Model entity);
}