package com.product.phoneshop.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelDTO {

    private Integer id;
    private String name;
    //private Integer brandId;
    private BrandDTO brandDTO;
}
