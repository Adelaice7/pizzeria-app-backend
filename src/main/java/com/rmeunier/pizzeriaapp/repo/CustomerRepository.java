package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
