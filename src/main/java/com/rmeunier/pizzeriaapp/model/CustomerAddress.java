package com.rmeunier.pizzeriaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "customer_address")
@NoArgsConstructor
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String streetAddress;
    private String city;
    private String zipCode;
    private String country;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerAddress(String streetAddress, String city, String zipCode, String country, Customer customer) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.customer = customer;
    }
}
