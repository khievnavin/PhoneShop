package com.product.phoneshop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {

    private long id;
    private String name;
    private Integer modelId;
    private Short yearMade;
    //private Integer colorId;
    private BigDecimal importPrice;
    private Double salePrice;
    private LocalDateTime dateImport;
    private String imagePath;
}
