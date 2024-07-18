package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Model;
import com.product.phoneshop.service.dto.ModelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(target = "brand" , source = "brandDTO")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandDTO" , source = "brand")
    ModelDTO toDTO(Model entity);
}