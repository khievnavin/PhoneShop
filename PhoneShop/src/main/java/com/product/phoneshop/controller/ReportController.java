package com.product.phoneshop.controller;

import com.product.phoneshop.projections.SaleByDate;
import com.product.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> getProductSaleByDate(@PathVariable LocalDate soldDate) {
        List<SaleByDate> productSaleByDate = reportService.getProductSaleByDate(soldDate);
        return ResponseEntity.ok(productSaleByDate);
    }
}
