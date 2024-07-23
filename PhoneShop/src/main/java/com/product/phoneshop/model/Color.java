package com.product.phoneshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "colors")
@Data
public class Color {

    @Id
    private Long id;
    private String color;
}
