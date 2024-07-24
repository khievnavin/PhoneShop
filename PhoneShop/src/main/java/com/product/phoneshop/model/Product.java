package com.product.phoneshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products" , uniqueConstraints = {@UniqueConstraint(columnNames = {"model_Id","year_made"})})
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

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    /*
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
     */

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "available_unit")
    private Integer availableUnit; //TODO should move to Stock to table? reason?

    @Column(name = "image_path")
    private String imagePath;
}
