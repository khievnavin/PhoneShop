package com.product.phoneshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "brands")
@Data

public class Brand {
    @Id
    @GeneratedValue(generator = "brand_seq_generator")
    @SequenceGenerator(name = "brand_seq_generator" , initialValue = 1, sequenceName = "brand_seq")
    private Integer id;
    private String name;

}
