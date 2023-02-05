package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
