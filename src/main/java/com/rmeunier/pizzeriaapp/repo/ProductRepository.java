package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p INNER JOIN p.category prc WHERE prc.name = :#{#category}")
    List<Product> findByCategoryName(@Param("category") String category);
}
