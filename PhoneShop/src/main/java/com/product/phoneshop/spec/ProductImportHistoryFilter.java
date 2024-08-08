package com.product.phoneshop.spec;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductImportHistoryFilter {
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean saleStatus;
}
