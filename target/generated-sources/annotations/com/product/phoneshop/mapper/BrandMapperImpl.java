package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.dto.BrandDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-18T14:51:18+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand toEntity(BrandDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( dto.getId() );
        brand.setName( dto.getName() );

        return brand;
    }

    @Override
    public BrandDTO toDTO(Brand entity) {
        if ( entity == null ) {
            return null;
        }

        BrandDTO.BrandDTOBuilder brandDTO = BrandDTO.builder();

        brandDTO.id( entity.getId() );
        brandDTO.name( entity.getName() );

        return brandDTO.build();
    }
}
