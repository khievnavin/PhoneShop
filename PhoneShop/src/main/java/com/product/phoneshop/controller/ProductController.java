package com.product.phoneshop.controller;

import com.product.phoneshop.dto.PageDTO;
import com.product.phoneshop.dto.PriceDTO;
import com.product.phoneshop.dto.ProductDisplayDTO;
import com.product.phoneshop.dto.ProductImportDTO;
import com.product.phoneshop.mapper.PageMapper;
import com.product.phoneshop.mapper.ProductMapper;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductImportDTO dto) {
        return ResponseEntity.ok(productMapper.toDTO(productService.save(dto)));
    }

    @PutMapping("/setPrice/{productId}")
    public ResponseEntity<?> setPrice(@PathVariable("productId") Long productId, @RequestBody PriceDTO priceDTO) {
        Product product = productService.setPrice(productId, priceDTO.getSalePrice());
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @GetMapping
    public ResponseEntity<?> ListProducts(@RequestParam Map<String, String> params) {
        Page<Product> productPage = productService.getProducts(params);

        PageDTO pageDTO = new PageDTO();
        pageDTO.setList(productService.toProductDisplayDTO(productPage.getContent()));

        //PageDTO pageDTO = PageMapper.INSTANCE.toDTO(productPage);
        return ResponseEntity.ok(pageDTO);
    }
}