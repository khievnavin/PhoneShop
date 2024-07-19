package com.product.phoneshop.service.impl;

import com.product.phoneshop.exception.ResourceNotFoundException;
import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.repository.ModelRepository;
import com.product.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    @Override
    public Model save(Model entity) {
        return modelRepository.save(entity);
    }

    @Override
    public Model getById(Integer id) throws ServiceException {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model" , id));

    }
}