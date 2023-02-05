package com.rmeunier.pizzeriaapp.repo;

import com.rmeunier.pizzeriaapp.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findByName(String name);
}
