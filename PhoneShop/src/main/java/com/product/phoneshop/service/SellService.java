package com.product.phoneshop.service;

import com.product.phoneshop.dto.SaleDTO;
import com.product.phoneshop.model.Sale;

public interface SellService {
    void sell(SaleDTO saleDTO);
    void cancelSale(Long saleId);
    Sale getById(Long saleId);
}
