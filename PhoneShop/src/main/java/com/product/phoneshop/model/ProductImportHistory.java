package com.product.phoneshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_import_histories")
@Data
public class ProductImportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "date_import")
    private LocalDate dateImport;

    @DecimalMin(value = "0.000001")
    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Column(name = "import_unit")
    private Integer importUnit;
}
