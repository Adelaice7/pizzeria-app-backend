package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "order_details")
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double total;

    @OneToOne(mappedBy = "orderDetails")
    private Customer customer;

    @OneToMany(mappedBy = "orderDetails")
    private Set<OrderItems> orderItems;

    private long createdAt = new Date().getTime();

    private long modifiedAt = new Date().getTime();

    public OrderDetails(double total, Customer customer) {
        this.total = total;
        this.customer = customer;
    }
}
