package com.rmeunier.pizzeriaapp.shared;

import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.repo.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(value);
        if (optionalCustomer.isEmpty()) {
            return true;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + value);
        }
    }
}
