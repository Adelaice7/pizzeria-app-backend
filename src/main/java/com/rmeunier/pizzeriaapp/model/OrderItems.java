package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_details_id", nullable = false)
    private OrderDetails orderDetails;

    @OneToOne(mappedBy = "orderItems")
    private Product product;

    private long createdAt = new Date().getTime();


    private long modifiedAt = new Date().getTime();

    public OrderItems(int quantity, OrderDetails orderDetails, Product product) {
        this.quantity = quantity;
        this.orderDetails = orderDetails;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
