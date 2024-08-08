package com.product.phoneshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products" , uniqueConstraints = {@UniqueConstraint(columnNames = {"model_Id","year_made"})})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "available_unit")
    private Integer availableUnit; //TODO should move to Stock to table? reason?

    @Column(name = "image_path")
    private String imagePath;
}
