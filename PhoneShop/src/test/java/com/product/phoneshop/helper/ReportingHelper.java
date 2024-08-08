package com.product.phoneshop.helper;

import com.product.phoneshop.model.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReportingHelper {

    private static final Sale sale1 = new Sale(1L, LocalDateTime.now().minusDays(-5), true);
    private static final Sale sale2 = new Sale(2L, LocalDateTime.now().minusDays(-2), true);
    private static final Brand brand1 = new Brand(1L,"Apple",true);
    private static final Model model1 = new Model(1L,"Iphone 15",brand1,2024);
    private static final Color color1 = new Color(1L, "white");
    private static final Color color2 = new Color(1L, "blue");
    private static final Product product1 = Product.builder()
            .id(1L)
            .name("Iphone 15 white")
            .availableUnit(10)
            .color(color1)
            .model(model1)
            .salePrice(BigDecimal.valueOf(1500))
            .build();
    private static final Product product2 = Product.builder()
            .id(2L)
            .name("Iphone 15 blue")
            .availableUnit(10)
            .color(color2)
            .model(model1)
            .salePrice(BigDecimal.valueOf(1500))
            .build();
    public static List<SaleDetail> getDetails(){
        SaleDetail saleDetail1 = SaleDetail.builder()
                .id(1L)
                .sale(sale1)
                .amount(BigDecimal.valueOf(200))
                .product(product1)
                .unit(5)
                .build();
        SaleDetail saleDetail2 = SaleDetail.builder()
                .id(2L)
                .sale(sale1)
                .amount(BigDecimal.valueOf(300))
                .product(product2)
                .unit(10)
                .build();
        SaleDetail saleDetail3 = SaleDetail.builder()
                .id(3L)
                .sale(sale2)
                .amount(BigDecimal.valueOf(300))
                .product(product2)
                .unit(4)
                .build();
        return List.of(saleDetail1, saleDetail2, saleDetail3);
    }

    public static List<Product> getProducts(){
        return List.of(product1, product2);
    }
}
