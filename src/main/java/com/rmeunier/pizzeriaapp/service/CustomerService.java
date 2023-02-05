package com.rmeunier.pizzeriaapp.service;

import com.rmeunier.pizzeriaapp.model.Customer;

import java.util.Set;

public interface CustomerService {
    void saveCustomer(Customer customer);
    void updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);

    Customer getCustomer(Long id);
    Set<Customer> getAllCustomers();
}
