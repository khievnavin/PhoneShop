package com.product.phoneshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDisplayDTO {

    private Long id;
    private String name;
    private String model;
    private String color;
    private Double salePrice;
}