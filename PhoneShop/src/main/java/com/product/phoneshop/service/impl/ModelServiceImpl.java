package com.product.phoneshop.service.impl;

import com.product.phoneshop.model.Model;
import com.product.phoneshop.repository.ModelRepository;
import com.product.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    @Override
    public Model save(Model entity) {
        return modelRepository.save(entity);
    }
}