package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "shopping_session")
@NoArgsConstructor
public class ShoppingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double total;

    @OneToMany(mappedBy = "session")
    private Set<CartItem> cartItems;

    private long createdAt = new Date().getTime();

    private long modifiedAt = new Date().getTime();

    public ShoppingSession(double total, Set<CartItem> cartItems) {
        this.total = total;
        this.cartItems = cartItems;
    }
}
