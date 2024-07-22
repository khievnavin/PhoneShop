package com.product.phoneshop.mapper;

import com.product.phoneshop.service.dto.PageDTO;
import com.product.phoneshop.service.dto.PaginationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface PageMapper {
    PageMapper INSTANCE = Mappers.getMapper(PageMapper.class);

    @Mapping(target = "pagination", expression = "java(toPaginationDTO(page))")
    @Mapping(target = "list", expression = "java(page.getContent())")
    PageDTO toDTO(Page<?> page);

    PaginationDTO toPaginationDTO(Page<?> page);
}