package com.product.phoneshop.mapper;

import com.product.phoneshop.dto.ProductDTO;
import com.product.phoneshop.dto.ProductDTO2;
import com.product.phoneshop.dto.ProductImportDTO;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.service.ColorService;
import com.product.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model" , source = "dto.modelId")
    @Mapping(target = "color", source = "dto.colorId")
    Product toProduct(ProductDTO dto);

    @Mapping(target = "modelId" , source = "model.id")
    @Mapping(target = "colorId" , source = "color.id")
    ProductDTO2 toDTO(Product entity);
}