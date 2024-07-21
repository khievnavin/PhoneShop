package com.product.phoneshop.service;

import com.product.phoneshop.model.Model;

import java.util.List;
import java.util.Map;

public interface ModelService {

    Model save(Model dto) ;
    Model getById(Integer id);
    List<Model> getModels(Map<String,String> params);   //we want to use dynamic query by using Map
}