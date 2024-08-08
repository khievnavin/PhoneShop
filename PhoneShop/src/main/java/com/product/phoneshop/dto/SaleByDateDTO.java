package com.product.phoneshop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleByDateDTO {
    private LocalDate soldDate;

    private Long productId;

    private String productName;

    private Integer totalUnit;

    private Double amount;
}
