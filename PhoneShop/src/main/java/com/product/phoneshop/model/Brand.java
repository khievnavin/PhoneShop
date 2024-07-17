package com.product.phoneshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "brands")
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;

}
