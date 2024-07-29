package com.product.phoneshop.projections;

import java.time.LocalDate;

public interface SaleByDate {

    LocalDate getLSoldDate();

    Long getProductId();

    String getProductName();

    Long getTotalUnit();

    Double getAmount();
}
