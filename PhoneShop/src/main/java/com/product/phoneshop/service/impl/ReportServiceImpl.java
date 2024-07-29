package com.product.phoneshop.service.impl;

import com.product.phoneshop.projections.SaleByDate;
import com.product.phoneshop.repository.SaleDetailRepository;
import com.product.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final SaleDetailRepository saleDetailRepository;

    @Override
    public List<SaleByDate> getProductSaleByDate(LocalDate soldDate) {
        return saleDetailRepository.findByProduct(soldDate);
    }

}
