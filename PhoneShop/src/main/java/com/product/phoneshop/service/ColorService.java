package com.product.phoneshop.service;

import com.product.phoneshop.model.Color;

public interface ColorService {
    Color save(Color color);
    Color getById(Long id);
}
