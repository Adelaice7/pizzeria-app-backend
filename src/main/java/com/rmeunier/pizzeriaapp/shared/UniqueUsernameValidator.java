package com.rmeunier.pizzeriaapp.shared;

import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.repo.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Customer inDB = customerRepository.findByUsername(value);
        return inDB == null;
    }
}
