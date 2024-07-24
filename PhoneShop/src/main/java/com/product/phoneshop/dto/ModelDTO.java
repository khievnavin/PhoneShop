package com.product.phoneshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ModelDTO {
    @NotNull( message = "")
    private Integer id;
    private String name;
    private  Integer brandId;
    //private BrandDTO brandDTO;
    private  Integer yearMade;
}
