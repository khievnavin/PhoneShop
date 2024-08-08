package com.product.phoneshop.spec;

import com.product.phoneshop.model.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {
    private final ProductImportHistoryFilter importFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> productImport, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if(importFilter.getStartDate() != null) {
            LocalDateTime startDateTime = importFilter.getStartDate().atStartOfDay();
            Predicate startDate = cb.greaterThanOrEqualTo(productImport.get("dateImport"), startDateTime);
            predicates.add(startDate);
        }

        if(importFilter.getEndDate() != null) {
            LocalDateTime endDateTime = importFilter.getEndDate().atTime(LocalTime.MAX);
            Predicate endDate = cb.lessThanOrEqualTo(productImport.get("dateImport"), endDateTime);
            predicates.add(endDate);
        }

        Predicate[] predicateArr = predicates.toArray(Predicate[]::new);
        return cb.and(predicateArr);
    }


}
