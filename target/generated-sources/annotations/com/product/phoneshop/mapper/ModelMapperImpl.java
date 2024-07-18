package com.product.phoneshop.mapper;

import com.product.phoneshop.model.Model;
import com.product.phoneshop.service.dto.ModelDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-17T16:08:29+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ModelMapperImpl implements ModelMapper {

    @Override
    public Model toModel(ModelDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Model model = new Model();

        model.setId( dto.getId() );

        return model;
    }
}
