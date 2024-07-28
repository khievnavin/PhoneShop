package com.product.phoneshop.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {
    private List<ProductOrderDTO> products;
    /*
    pro. :
    [
    { productId: 1, unit: 2}
    { productId: 2, unit: 4}
    ]
     */
    private String soldDate;
}
