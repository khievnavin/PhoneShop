package com.product.phoneshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @NotNull
    @Column(name = "year_made")
    private Short yearMade;

    /*
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
     */

    @DecimalMin(value = "0.000001")
    @Column(name = "import_price")
    private BigDecimal importPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "date_import")
    private LocalDateTime dateImport;

    //private Integer numberOfUnit; //TODO should move to Stock to table? reason?

    @Column(name = "image_path")
    private String imagePath;
}
