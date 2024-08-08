package com.product.phoneshop.service.impl;

import com.product.phoneshop.dto.ExpenseDTO;
import com.product.phoneshop.dto.ProductSoldDTO;
import com.product.phoneshop.dto.SaleByDateDTO;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.model.ProductImportHistory;
import com.product.phoneshop.model.SaleDetail;
import com.product.phoneshop.projections.SaleByDate;
import com.product.phoneshop.repository.ProductImpHisRepository;
import com.product.phoneshop.repository.ProductRepository;
import com.product.phoneshop.repository.SaleDetailRepository;
import com.product.phoneshop.service.ReportService;
import com.product.phoneshop.spec.ProductImportHistoryFilter;
import com.product.phoneshop.spec.ProductImportHistorySpec;
import com.product.phoneshop.spec.SaleDetailFilter;
import com.product.phoneshop.spec.SaleDetailSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;
    private final ProductImpHisRepository productImpHisRepository;

    @Override
    public List<SaleByDate> getProductSaleByDate(LocalDate soldDate) {
        return saleDetailRepository.findByProduct(soldDate);
    }

    @Override
    public List<SaleByDateDTO> getProductSoldByDateV2(LocalDate soldDate) {
        SaleDetailFilter detailFilter = new SaleDetailFilter();
        detailFilter.setSoldDate(soldDate);

        SaleDetailSpec spec = new SaleDetailSpec(detailFilter);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);
        // Group by product
        Map<Product, List<SaleDetail>> saleByProductMap = saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));

        List<Long> productIds = saleDetails.stream()
                .map(sd -> sd.getProduct().getId())
                .toList();
        // Find all product sold in that day
        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        List<SaleByDateDTO> saleByDateDTOs = new ArrayList<>();
        for(Map.Entry<Product, List<SaleDetail>> entry : saleByProductMap.entrySet()) {

            var product = productMap.get(entry.getKey().getId());
            List<SaleDetail> saleDetailsList = entry.getValue();


            Integer totalUnit = saleDetailsList.stream().mapToInt(SaleDetail::getUnit).sum();

            Double amount = saleDetailsList.stream().mapToDouble(sd -> sd.getAmount().doubleValue() * sd.getUnit()).sum();


            SaleByDateDTO dto = new SaleByDateDTO();
            dto.setSoldDate(soldDate);
            dto.setProductId(product.getId());
            dto.setProductName(product.getName());
            dto.setTotalUnit(totalUnit);
            dto.setAmount(amount);
            saleByDateDTOs.add(dto);
        }

        return saleByDateDTOs;
    }
    @Override
    public List<ProductSoldDTO> getProductSold(LocalDate startDate, LocalDate endDate) {
        List<SaleDetail> saleDetails = getDetails(startDate, endDate);
        // Group by product
        Map<Product, List<SaleDetail>> saleByProductMap = saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));

        Set<Long> productIds = saleDetails.stream()
                .map(sd -> sd.getProduct().getId())
                .collect(Collectors.toSet());
        // Find all product sold in that day
        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        List<ProductSoldDTO> productSoldDTOs = new ArrayList<>();
        for(Map.Entry<Product, List<SaleDetail>> entry : saleByProductMap.entrySet()) {

            Product product = productMap.get(entry.getKey().getId());
            List<SaleDetail> saleDetailsList = entry.getValue();

            int totalUnit = saleDetailsList.stream().mapToInt(SaleDetail::getUnit).sum();

            Double amount = saleDetailsList.stream().mapToDouble(sd -> sd.getAmount().doubleValue() * sd.getUnit()).sum();


            ProductSoldDTO productSoldDTO = toProductSoldDTO(product,totalUnit, amount);
            productSoldDTOs.add(productSoldDTO);
        }

        productSoldDTOs.sort((a, b) -> (int) (a.getProductId() - b.getProductId()));

        return productSoldDTOs;
    }

    private ProductSoldDTO toProductSoldDTO(Product product, int totalUnit, Double amount) {
        ProductSoldDTO dto = new ProductSoldDTO();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setTotalUnit(totalUnit);
        dto.setAmount(amount);
        return dto;
    }

    private List<SaleDetail> getDetails(LocalDate startDate, LocalDate endDate){
        SaleDetailFilter detailFilter = new SaleDetailFilter();
        detailFilter.setStartDate(startDate);
        detailFilter.setEndDate(endDate);
        detailFilter.setSaleStatus(true);

        SaleDetailSpec spec = new SaleDetailSpec(detailFilter);
        return saleDetailRepository.findAll(spec);
    }

    @Override
    public List<ExpenseDTO> getExpense(LocalDate startDate, LocalDate endDate) {
        ProductImportHistoryFilter historyFilter = new ProductImportHistoryFilter();
        historyFilter.setStartDate(startDate);
        historyFilter.setEndDate(endDate);

        ProductImportHistorySpec historySpec = new ProductImportHistorySpec(historyFilter);

        List<ProductImportHistory> productImportHistories = productImpHisRepository.findAll(historySpec);

        Map<Product, List<ProductImportHistory>> historyMap = productImportHistories.stream()
                .collect(Collectors.groupingBy(ProductImportHistory::getProduct));

        var productIds = productImportHistories.stream()
                .map(h -> h.getProduct().getId())
                .toList();

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));


        List<ExpenseDTO> expenseDTOs = new ArrayList<>();

        for(var entry : historyMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getId());
            List<ProductImportHistory> historyList = entry.getValue();

            double amount = historyList.stream().mapToDouble(history -> history.getPricePerUnit().doubleValue() * history.getImportUnit()).sum();

            Integer unit = historyList.stream().mapToInt(ProductImportHistory::getImportUnit).sum();

            ExpenseDTO expenseDTO = new ExpenseDTO();
            expenseDTO.setProductId(product.getId());
            expenseDTO.setProductName(product.getName());
            expenseDTO.setAmount(BigDecimal.valueOf(amount));
            expenseDTO.setQuantity(unit);
            expenseDTOs.add(expenseDTO);
        }
        System.out.println(expenseDTOs);
        return expenseDTOs;
    }

}


