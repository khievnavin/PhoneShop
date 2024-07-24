package com.product.phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO2 {

    private long id;
    private String name;
    private Integer modelId;
    private Integer colorId;
    private BigDecimal importPrice;
    private Double salePrice;
    private LocalDateTime dateImport;
    private String imagePath;
}
