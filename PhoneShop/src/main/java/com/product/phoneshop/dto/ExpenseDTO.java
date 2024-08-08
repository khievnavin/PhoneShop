package com.product.phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal amount;
}
