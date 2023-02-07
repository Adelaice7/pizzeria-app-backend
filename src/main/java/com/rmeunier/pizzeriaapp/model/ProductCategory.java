package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "product_category")
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "description")
    private String desc;

    private long createdAt = new Date().getTime();

    private long modifiedAt = new Date().getTime();

    private long deletedAt = -1L;

    public ProductCategory(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
