package com.product.phoneshop.service;

import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.model.Model;

public interface ModelService {

    Model save(Model dto) ;
    Model getById(Integer id) ;
}