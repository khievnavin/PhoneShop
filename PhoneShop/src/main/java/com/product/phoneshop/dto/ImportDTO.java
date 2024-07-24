package com.product.phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ImportDTO {

    private Integer importUnit;
    private BigDecimal pricePerUnit;
    private LocalDate dateImport;
}
