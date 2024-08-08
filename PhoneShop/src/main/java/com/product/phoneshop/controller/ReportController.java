package com.product.phoneshop.controller;

import com.product.phoneshop.dto.ExpenseDTO;
import com.product.phoneshop.dto.ProductSoldDTO;
import com.product.phoneshop.dto.SaleByDateDTO;
import com.product.phoneshop.projections.SaleByDate;
import com.product.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor

public class ReportController {

    private final ReportService reportService;

    @GetMapping("/dailyProduct/{soldDate}")
    public ResponseEntity<?> getProductSaleByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate soldDate) {
        List<SaleByDate> productSaleByDate = reportService.getProductSaleByDate(soldDate);
        return ResponseEntity.ok(productSaleByDate);
    }
    @GetMapping("/dailyProduct/v2/{soldDate}")
    public ResponseEntity<?> getProductSaleByDateV2(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate soldDate){
        List<SaleByDateDTO> productSoldByDate = reportService.getProductSoldByDateV2(soldDate);
        return ResponseEntity.ok(productSoldByDate);
    }

    @GetMapping("/product/{startDate}/{endDate}")
    public ResponseEntity<?> getProductSold(
            @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        List<ProductSoldDTO> productSold = reportService.getProductSold(startDate, endDate);
        return ResponseEntity.ok(productSold);
    }

    @GetMapping("/expense/{startDate}/{endDate}")
    public ResponseEntity<?> getExpense(
            @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        List<ExpenseDTO> expenseDTOs = reportService.getExpense(startDate, endDate);
        return ResponseEntity.ok(expenseDTOs);
    }
}