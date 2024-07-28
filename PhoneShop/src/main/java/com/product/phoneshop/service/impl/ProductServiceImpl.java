package com.product.phoneshop.service.impl;

import com.product.phoneshop.dto.ProductDisplayDTO;
import com.product.phoneshop.dto.ProductImportDTO;
import com.product.phoneshop.exception.ResourceNotFoundException;
import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.mapper.ProductImportHistoryMapper;
import com.product.phoneshop.mapper.ProductMapper;
import com.product.phoneshop.model.Color;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.model.ProductImportHistory;
import com.product.phoneshop.repository.ColorRepository;
import com.product.phoneshop.repository.ModelRepository;
import com.product.phoneshop.repository.ProductImpHisRepository;
import com.product.phoneshop.repository.ProductRepository;
import com.product.phoneshop.service.ProductService;
import com.product.phoneshop.spec.ProductSpec;
import com.product.phoneshop.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImpHisRepository productImpHisRepository;
    private final ProductMapper productMapper;
    private final ModelRepository modelRepository;
    private final ColorRepository colorRepository;

    @Override
    public Product save(ProductImportDTO dto) {

        Long modelId = dto.getProduct().getModelId();
        Long colorId = dto.getProduct().getColorId();
        Optional<Product> existingProduct = productRepository.findByModelIdAndColorId(modelId, colorId);
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


        } else {
            product = productMapper.toProduct(dto.getProduct());
            product.setAvailableUnit(dto.getImportDetail().getImportUnit());
        }
        product = productRepository.save(product);
        //set product import history
        ProductImportHistory importHistory = ProductImportHistoryMapper.INSTANCE.toEntity(dto.getImportDetail(), product);
        productImpHisRepository.save(importHistory);

        return product;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", id));
    }

    @Override
    public Product setPrice(Long productId, BigDecimal price) {
        //check if product exit , get product
        Product product = getById(productId);
        //update price
        product.setSalePrice(price);
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProducts(Map<String, String> params) {
        Pageable pageable = PageUtils.getPageable(params);
        return productRepository.findAll(new ProductSpec(), pageable);
    }

    public List<ProductDisplayDTO> toProductDisplayDTO(List<Product> products) {
        List<ProductDisplayDTO> displayDTOs = new ArrayList<>();
        //products find all model id:
        List<Long> modelIds = products.stream().map(p -> p.getModel().getId()).toList();
        List<Model> models = modelRepository.findAllById(modelIds);
        Map<Long, String> modelMap = models.stream().collect(Collectors.toMap(Model::getId, Model::getName));

        List<Long> colorIds = products.stream().map(p -> p.getColor().getId()).toList();
        List<Color> colors = colorRepository.findAllById(colorIds);

        Map<Long, String> colorMap = colors.stream().collect(Collectors.toMap(
                Color::getId,
                Color::getName
        ));


        for (Product product : products) {
            ProductDisplayDTO dto = new ProductDisplayDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setSalePrice(product.getSalePrice());
            dto.setModel(modelMap.get(product.getModel().getId()));
            dto.setColor(colorMap.get(product.getColor().getId()));
            displayDTOs.add(dto);
        }
        return displayDTOs;
    }

    @Override
    public boolean hasAvailableUnit(Long productId, Integer orderUnit) {
        Product product = getById(productId);
        if (product.getAvailableUnit() < orderUnit) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product (%s) with id: %s has no available unit".formatted(product.getName(), productId)) ;
        }
        return true;
    }

    @Override
    public boolean salePriceIsSet(Long productId) {
        Product product = getById(productId);
        if (Objects.isNull(product.getSalePrice())){ //or product.getSalePrice() == null
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product (%s) with id: %s haven't price yet".formatted(product.getName(), productId)) ;
        }
        return false;
    }
}
