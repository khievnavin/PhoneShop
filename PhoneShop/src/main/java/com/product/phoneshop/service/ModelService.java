package com.product.phoneshop.service;

import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.model.Model;

public interface ModelService {

    Model save(Model dto) throws  SecurityException;
    Model getById(Integer id) throws ServiceException;
}