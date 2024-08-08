package com.product.phoneshop.service;

import com.product.phoneshop.dto.ProductSoldDTO;
import com.product.phoneshop.helper.ReportingHelper;
import com.product.phoneshop.model.Product;
import com.product.phoneshop.model.SaleDetail;
import com.product.phoneshop.repository.*;
import com.product.phoneshop.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepostingServiceTest {

    @Mock
    private SaleDetailRepository saleDetailRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private  ProductImpHisRepository productImpHisRepository;
    private ReportService reportService;


    @BeforeEach
    public void setup(){
         reportService = new ReportServiceImpl(saleDetailRepository, productRepository, productImpHisRepository);
    }

    @Test
    public void testGetProductSold() {
        //GIVE
        List<SaleDetail> saleDetails = ReportingHelper.getDetails();
        List<Product> products = ReportingHelper.getProducts();
        //WHEN
        when(saleDetailRepository.findAll(any(Specification.class))).thenReturn(saleDetails);
//        when(productRepository.findAllById(anyList())).thenReturn(products);
        //WHEN
      //  when(saleDetailRepository.findAll(any(Specification.class))).thenReturn(saleDetails);

        // Match any collection instead of just a list
        when(productRepository.findAllById(any(Collection.class))).thenReturn(products);
        List<ProductSoldDTO> productSoldDTOs =  reportService.getProductSold(LocalDate.now().minusDays(-10), LocalDate.now());
        //THEN
        assertEquals(2, productSoldDTOs.size());

        ProductSoldDTO productSoldDTO1 = productSoldDTOs.get(0);
        assertEquals(1L, productSoldDTO1.getProductId());
        assertEquals("Iphone 15 white", productSoldDTO1.getProductName());
        assertEquals(5, productSoldDTO1.getTotalUnit());
        assertEquals(1000d, productSoldDTO1.getAmount().doubleValue());

        ProductSoldDTO productSoldDTO2 = productSoldDTOs.get(1);
        assertEquals(2L, productSoldDTO2.getProductId());
        assertEquals("Iphone 15 blue", productSoldDTO2.getProductName());
        assertEquals(14, productSoldDTO2.getTotalUnit());
        assertEquals(4200d, productSoldDTO2.getAmount().doubleValue());
    }
}
