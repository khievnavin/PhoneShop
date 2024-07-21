package com.product.phoneshop.spec;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.model.Model;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ModelSpec implements Specification<Model> {
    private final ModelFilter modelFilter;

    @Override
    public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Join<Model, Brand> brand = model.join("brand");
        var list = new ArrayList<>();
        if(modelFilter.getModelId() != null ){
            Predicate modelId = model.get("id").in(modelFilter.getModelId());
            list.add(modelId);
        }
        if(modelFilter.getModelName() != null ){
            Predicate modelName = cb.like(model.get("name"), "%"+modelFilter.getModelName()+"%");
            list.add(modelName);
        }
        if(modelFilter.getBrandId() != null ){
           Predicate brandId = brand.get("id").in(modelFilter.getBrandId());
           list.add(brandId);
        }
        if(modelFilter.getBrandName() != null ){
            Predicate brandName = cb.like(brand.get("name"), "%"+modelFilter.getBrandName()+"%");
            list.add(brandName);
        }

        Predicate[] predicates = list.toArray(Predicate[]::new);
        return cb.and(predicates);
    }
}
