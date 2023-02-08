package com.rmeunier.pizzeriaapp.service;

import com.rmeunier.pizzeriaapp.error.ProductCategoryNotFoundException;
import com.rmeunier.pizzeriaapp.model.Product;
import com.rmeunier.pizzeriaapp.model.ProductCategory;

import java.util.Set;

public interface ProductService {

    void saveProduct(Product product) throws ProductCategoryNotFoundException;

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Product getProduct(Long id);

    Set<Product> getAllProducts();

    Set<Product> getProductsOfCategory(ProductCategory category);
}
