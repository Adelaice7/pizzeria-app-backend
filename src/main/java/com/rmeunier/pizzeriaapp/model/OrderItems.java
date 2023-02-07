package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
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
}
