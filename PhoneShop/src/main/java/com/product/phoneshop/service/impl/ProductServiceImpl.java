package com.product.phoneshop.service.impl;

import com.product.phoneshop.dto.ProductImportDTO;
import com.product.phoneshop.exception.ResourceNotFoundException;
import com.product.phoneshop.mapper.ProductImportHistoryMapper;
import com.product.phoneshop.mapper.ProductMapper;
import com.product.phoneshop.model.Color;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.model.ProductImportHistory;
import com.product.phoneshop.repository.ProductImpHisRepository;
import com.product.phoneshop.repository.ProductRepository;
import com.product.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private  final ProductImpHisRepository productImpHisRepository;
    private  final ProductMapper productMapper;
    //    @Override
//    public Product save(ProductDTO productDto) {
//        return productRepository.save(productMapper.toProduct(productDto));
//    }

    @Override
    public Product save(ProductImportDTO dto) {

        Long modelId = dto.getProduct().getModelId();
        Long colorId = dto.getProduct().getColorId();
        Optional<Product> existingProduct = productRepository.findByModelIdAndColorId(modelId,colorId);
        Product product = null;
        if (existingProduct.isPresent()) {
            /* product
             for set new available unit in stock
             get current available unit  + new  number unit
             */
            product = existingProduct.get();
            Integer availableUnit = product.getAvailableUnit();
            Integer importUnit = dto.getImportDetail().getImportUnit();
            product.setAvailableUnit(availableUnit + importUnit); //get new value


        }else {
            product = productMapper.toProduct(dto.getProduct());
            product.setAvailableUnit(dto.getImportDetail().getImportUnit());
        }
        product = productRepository.save(product);
        //set product import history
       ProductImportHistory importHistory = ProductImportHistoryMapper.INSTANCE.toEntity(dto.getImportDetail(),product);
        productImpHisRepository.save(importHistory);

        return product;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found",id));
    }
}
