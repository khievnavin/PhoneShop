package com.product.phoneshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//work with jpa
@Entity
@Table(name = "models")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(generator = "model_seq_generator")
    @SequenceGenerator(name = "model_seq_generator", initialValue = 1, sequenceName = "model_seq")
    private Long id;

    @NotBlank
    @Column(name = "model_name") //for custom name table
    private String name;

    @ManyToOne(fetch = FetchType.EAGER) //Lazy for want to know about info not early repaid , Eager repaid
    @JoinColumn(name = "brand_id") //for custom name table
    private Brand brand;

    @NotNull
    @Column(name = "year_made")
    private Integer yearMade;
}
