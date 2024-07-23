package com.product.phoneshop.mapper;

import com.product.phoneshop.dto.ProductDTO;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {ModelService.class})
public interface ProductMapper {

    @Mapping(target = "model" , source = "dto.modelId")
    Product toProduct(ProductDTO dto);

    @Mapping(target = "modelId" , source = "model.id")
    ProductDTO toDTO(Product entity);
}