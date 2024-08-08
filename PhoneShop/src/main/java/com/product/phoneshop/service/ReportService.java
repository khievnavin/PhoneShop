package com.product.phoneshop.service;

import com.product.phoneshop.dto.ExpenseDTO;
import com.product.phoneshop.dto.ProductSoldDTO;
import com.product.phoneshop.dto.SaleByDateDTO;
import com.product.phoneshop.projections.SaleByDate;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    //daily sale by product
    List<SaleByDate> getProductSaleByDate(LocalDate soldDate);

    List<SaleByDateDTO> getProductSoldByDateV2(LocalDate soldDate);

    List<ProductSoldDTO> getProductSold(LocalDate startDate, LocalDate endDate);

    List<ExpenseDTO> getExpense(LocalDate startDate, LocalDate endDate);
}
