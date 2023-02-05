package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(long modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
