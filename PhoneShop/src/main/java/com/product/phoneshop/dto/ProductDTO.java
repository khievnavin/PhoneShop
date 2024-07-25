package com.product.phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private String name;
    private Long modelId;
    private Long colorId;
}
