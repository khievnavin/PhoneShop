package com.product.phoneshop.service.impl;

import com.product.phoneshop.exception.ResourceNotFoundException;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.repository.ModelRepository;
import com.product.phoneshop.service.ModelService;
import com.product.phoneshop.spec.ModelFilter;
import com.product.phoneshop.spec.ModelSpec;
import com.product.phoneshop.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Override
    public Model save(Model entity) {
        return modelRepository.save(entity);
    }

    //Get By ID
    @Override
    public Model getById(Integer id)  {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model" , id));

    }

    @Override
    public Page<Model> getModels(Map<String, String> params) {
       // Pageable pageable = PageRequest.of(PageUtils.PAGE_NUMBER_DEFAULT,PageUtils.PAGE_SIZE_DEFAULT);
        Pageable pageable = PageUtils.getPageable(params);

        ModelFilter modelFilter = new ModelFilter();

        if(params.containsKey("modelId")) {
            modelFilter.setModelId(MapUtils.getInteger(params, "modelId"));
        }
        if(params.containsKey("modelName")) {
            modelFilter.setModelName(MapUtils.getString(params, "modelName"));
        }
        if(params.containsKey("brandId")) {
            modelFilter.setBrandId(MapUtils.getInteger(params, "brandId"));
        }
        if(params.containsKey("brandName")) {
            modelFilter.setBrandName(MapUtils.getString(params, "brandName"));
        }
        ModelSpec modelSpec = new ModelSpec(modelFilter);

        return modelRepository.findAll(modelSpec, pageable);
    }

  /*
    //@Override
    public List<Model> getModels(Map<String, String> params) {
        ModelFilter modelFilter = new ModelFilter();
        if(params.containsKey("modelId")) {
            modelFilter.setModelId(MapUtils.getInteger(params, "modelId"));
        }
        if(params.containsKey("modelName")) {
            modelFilter.setModelName(MapUtils.getString(params, "modelName"));
        }
        if(params.containsKey("brandId")) {
            modelFilter.setBrandId(MapUtils.getInteger(params, "brandId"));
        }
        if(params.containsKey("brandName")) {
            modelFilter.setBrandName(MapUtils.getString(params, "brandName"));
        }
        ModelSpec modelSpec = new ModelSpec(modelFilter);
        return modelRepository.findAll(modelSpec, Sort.by(Sort.Order.asc("id")));
    }
    */
  //Get All
  /*  @Override
    public List<Model> getModelsOld(Map<String, String> params) {
       // params.containsKey("name");
        Specification<Model> specification = new Specification<Model>() {

            @Override
            public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (params.containsKey("name")) {
                    String modelName = params.get("name");
                    //Predicate predicateName = cb.like(model.get("name"), "%" + modelName + "%");
                     return cb.equal(model.get("name"), modelName);
                   // return predicateName;
                }
                return null;
            }
        };

        return modelRepository.findAll(specification , Sort.by(Sort.Order.asc("id")));
    }*/
}