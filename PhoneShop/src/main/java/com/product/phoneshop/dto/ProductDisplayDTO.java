package com.product.phoneshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDisplayDTO {

    private Long id;
    private String name;
    private String model;
    private String color;
    private BigDecimal salePrice;
}