package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "description")
    private String desc;
    private double price;

    @OneToOne(mappedBy = "product")
    private CartItem cartItem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_items_id", referencedColumnName = "id")
    private OrderItems orderItems;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    private long createdAt = new Date().getTime();

    private long modifiedAt = new Date().getTime();

    private long deletedAt = -1L;

    public Product(String name, String desc, double price, CartItem cartItem, OrderItems orderItems, ProductCategory category) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.cartItem = cartItem;
        this.orderItems = orderItems;
        this.category = category;
    }
}
