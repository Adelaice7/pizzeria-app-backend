package com.rmeunier.pizzeriaapp.service.impl;

import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.repo.CustomerRepository;
import com.rmeunier.pizzeriaapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        customerRepository.findById(id).ifPresent(oldCustomer -> {
            oldCustomer.setFirstName(customer.getFirstName());
            oldCustomer.setLastName(customer.getLastName());
            oldCustomer.setPhoneNumber(customer.getPhoneNumber());
            oldCustomer.setUsername(customer.getUsername());
            oldCustomer.setEmail(customer.getEmail());
            oldCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));

            oldCustomer.setModifiedAt(new Date().getTime());
            customerRepository.save(oldCustomer);
        });

    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresent(customer -> {
            customer.setDeletedAt(new Date().getTime());
        });
    }

    @Override
    public Customer getCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.get();
    }

    @Override
    public Set<Customer> getAllCustomers() {
        return new HashSet<>(customerRepository.findAll());
    }
}
