package com.product.phoneshop.service;

import com.product.phoneshop.projections.SaleByDate;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    //daily sale by product
    List<SaleByDate> getProductSaleByDate(LocalDate soldDate);
}
