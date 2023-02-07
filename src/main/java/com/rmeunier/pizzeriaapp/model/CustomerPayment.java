package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "customer_payment")
@NoArgsConstructor
public class CustomerPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String provider;
    private int accountNumber;
    private Date expiry;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerPayment(PaymentType paymentType, String provider, int accountNumber, Date expiry, Customer customer) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiry = expiry;
        this.customer = customer;
    }
}
