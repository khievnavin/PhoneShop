package com.product.phoneshop.mapper;

import com.product.phoneshop.dto.ImportDTO;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.model.ProductImportHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper

public interface ProductImportHistoryMapper {
    ProductImportHistoryMapper INSTANCE = Mappers.getMapper(ProductImportHistoryMapper.class);

    @Mapping(target = "product" , source = "product")
    @Mapping(target = "id" ,ignore = true)
    ProductImportHistory toEntity(ImportDTO importDTO , Product product);
}
