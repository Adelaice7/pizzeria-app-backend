package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "cart_item")
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private ShoppingSession session;

    private long createdAt = new Date().getTime();

    private long modifiedAt = new Date().getTime();

    public CartItem(int quantity, Product product, ShoppingSession session) {
        this.quantity = quantity;
        this.product = product;
        this.session = session;
    }
}
