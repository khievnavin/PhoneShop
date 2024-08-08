package com.product.phoneshop.spec;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleDetailFilter {
    private LocalDate soldDate;
    private Long productId;
    private Long modelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean saleStatus;
}
