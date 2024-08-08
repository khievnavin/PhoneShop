package com.product.phoneshop.controller;

import com.product.phoneshop.dto.SaleDTO;
import com.product.phoneshop.mapper.ProductMapper;
import com.product.phoneshop.service.ProductService;
import com.product.phoneshop.service.SellService;
import com.product.phoneshop.service.impl.SellServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sells")
@RequiredArgsConstructor

public class SaleController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final SellService sellService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SaleDTO dto){
        sellService.sell(dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{saleId}/cancel")
    public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
        sellService.cancelSale(saleId);
        return ResponseEntity.noContent().build();
    }
}
