package com.product.phoneshop.service.impl;

import com.product.phoneshop.dto.ProductOrderDTO;
import com.product.phoneshop.dto.SaleDTO;
import com.product.phoneshop.mapper.SaleMapper;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.model.Sale;
import com.product.phoneshop.model.SaleDetail;
import com.product.phoneshop.repository.ProductRepository;
import com.product.phoneshop.repository.SaleDetailRepository;
import com.product.phoneshop.repository.SaleRepository;
import com.product.phoneshop.service.ProductService;
import com.product.phoneshop.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellServiceImpl implements SellService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository sellDetailRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleMapper saleMapper;
    private final SaleDetailRepository saleDetailRepository;
    @Override
    public void sell(SaleDTO saleDTO) {
        List<ProductOrderDTO> productOrderDTOs = saleDTO.getProducts();
        //validate stock
        for (ProductOrderDTO orderDTO : productOrderDTOs) {
            //validate stock
            productService.hasAvailableUnit(orderDTO.getProductId(), orderDTO.getUnit());
            //validate price
            productService.salePriceIsSet(orderDTO.getProductId());
        }
        //get ordered products from db
        List<Long> productIds = productOrderDTOs.stream()
                .map(ProductOrderDTO::getProductId)
                .toList();

        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream().collect(Collectors.toMap(Product::getId, p -> p));

        //save sale
        Sale sale = saleMapper.toSale(saleDTO);
        saleRepository.save(sale);

        //save sale  Detail
        for (ProductOrderDTO orderDTO : productOrderDTOs) {
            Product product = productMap.get(orderDTO.getProductId());
            SaleDetail saleDetail = saleMapper.toSaleDetail(orderDTO, sale, product.getSalePrice());
            saleDetailRepository.save(saleDetail);

            //update stock
            product.setAvailableUnit(product.getAvailableUnit() - orderDTO.getUnit());
            productRepository.save(product);
        }
    }
}
