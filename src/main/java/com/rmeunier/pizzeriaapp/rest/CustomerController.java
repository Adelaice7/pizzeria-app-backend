package com.rmeunier.pizzeriaapp.rest;

import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.service.CustomerService;
import com.rmeunier.pizzeriaapp.shared.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public Set<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers")
    public GenericResponse createCustomer(@Valid @RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new GenericResponse("User saved.");
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomer(id);
    }
}
