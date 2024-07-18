package com.product.phoneshop.model;

import jakarta.persistence.*;
import lombok.Data;

//work with jpa
@Entity
@Table( name = "models")
@Data
public class Model {
    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(generator = "model_seq_generator")
    @SequenceGenerator(name = "model_seq_generator" , initialValue = 1, sequenceName = "model_seq")
    private Integer id;

  @Column(name = "model_name") //for custom name table
    private  String name;

    @ManyToOne(fetch = FetchType.LAZY) //Lazy for want to know about info not early repaid , Eager repaid
  @JoinColumn(name = "brand_id") //for custom name table
    private  Brand brand;
}
