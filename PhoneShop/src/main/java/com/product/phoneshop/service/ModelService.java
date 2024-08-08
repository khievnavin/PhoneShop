package com.product.phoneshop.service;

import com.product.phoneshop.model.Model;
import org.springframework.data.domain.Page;
import java.util.Map;

public interface ModelService {

    Model save(Model dto) ;
    Model getById(Long id);
    Page<Model> getModels(Map<String, String> params);
    //List<Model> getModels(Map<String,String> params);   //we want to use dynamic query by using Map
}