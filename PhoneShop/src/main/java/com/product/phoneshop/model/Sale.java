package com.product.phoneshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sales")

public class Sale {

    @Id
    private Long id;
    private LocalDateTime soleDate;
}
