package com.rmeunier.pizzeriaapp.service.impl;

import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.repo.CustomerRepository;
import com.rmeunier.pizzeriaapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
//            oldCustomer.setEmail(customer.getEmail());
            oldCustomer.setFirstName(customer.getFirstName());
            oldCustomer.setLastName(customer.getLastName());
            oldCustomer.setPhoneNumber(customer.getPhoneNumber());

            customerRepository.save(oldCustomer);
        });

    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresent(customer -> {
            customerRepository.delete(customer);
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
