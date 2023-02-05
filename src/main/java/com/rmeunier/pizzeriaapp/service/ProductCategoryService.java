package com.rmeunier.pizzeriaapp.service;

import com.rmeunier.pizzeriaapp.model.ProductCategory;

import java.util.Set;

public interface ProductCategoryService {
    void saveProductCategory(ProductCategory category);

    void updateProductCategory(Long id, ProductCategory category);

    void deleteProductCategory(Long id);

    ProductCategory getProductCategory(Long id);

    ProductCategory getProductCategoryByName(String name);

    Set<ProductCategory> getAllProductCategories();
}
