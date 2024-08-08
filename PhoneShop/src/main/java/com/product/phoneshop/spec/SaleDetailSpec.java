package com.product.phoneshop.spec;


import com.product.phoneshop.model.Sale;
import com.product.phoneshop.model.SaleDetail;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {
    private final SaleDetailFilter detailFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if(detailFilter.getSoldDate() != null) {

            LocalDate date = detailFilter.getSoldDate();

            LocalDateTime startDateTime = date.atStartOfDay();
            LocalDateTime endDateTime = date.atTime(LocalTime.MAX);

            Predicate soldDate = cb.between(sale.get("soldDate"), startDateTime, endDateTime);

            predicates.add(soldDate);
        }

        if(detailFilter.getStartDate() != null) {
            LocalDateTime startDateTime = detailFilter.getStartDate().atStartOfDay();
            Predicate startDate = cb.greaterThanOrEqualTo(sale.get("soldDate"), startDateTime);
            predicates.add(startDate);
        }

        if(detailFilter.getEndDate() != null) {
            LocalDateTime endDateTime = detailFilter.getEndDate().atTime(LocalTime.MAX);
            Predicate endDate = cb.lessThanOrEqualTo(sale.get("soldDate"), endDateTime);
            predicates.add(endDate);
        }

        if(Objects.nonNull(detailFilter.getSaleStatus())) {

            Predicate saleStatus = sale.get("status").in(detailFilter.getSaleStatus());
            predicates.add(saleStatus);
        }

        Predicate[] predicateArr = predicates.toArray(Predicate[]::new);
        return cb.and(predicateArr);
    }

}

