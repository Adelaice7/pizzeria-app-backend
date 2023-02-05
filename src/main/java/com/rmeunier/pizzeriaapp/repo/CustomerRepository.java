package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
