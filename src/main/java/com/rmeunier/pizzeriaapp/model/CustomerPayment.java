package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "customer_payment")
public class CustomerPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private PaymentType paymentType;
    private String provider;
    private int accountNumber;
    private Date expiry;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerPayment() {

    }

    public CustomerPayment(PaymentType paymentType, String provider, int accountNumber, Date expiry, Customer customer) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiry = expiry;
        this.customer = customer;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerPayment that = (CustomerPayment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
