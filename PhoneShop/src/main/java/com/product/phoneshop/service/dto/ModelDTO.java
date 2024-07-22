package com.product.phoneshop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ModelDTO {

    private Integer id;
    private String name;
    private  Integer brandId;
    //private BrandDTO brandDTO;

}
