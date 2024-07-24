package com.product.phoneshop.service.impl;

import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.model.Color;
import com.product.phoneshop.repository.ColorRepository;
import com.product.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    public final ColorRepository colorRepository;

    @Override
    public Color save(Color entity) {
        return colorRepository.save(entity);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, String.format("color not found for id= %d", id)));
    }
}
