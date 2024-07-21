package com.product.phoneshop.service.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BrandDTO {
    /*
     return information to user (req,res)
     */
    private Integer id;
    private String name;

}
