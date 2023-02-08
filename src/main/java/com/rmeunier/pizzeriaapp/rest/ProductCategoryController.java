package com.rmeunier.pizzeriaapp.rest;

import com.rmeunier.pizzeriaapp.model.ProductCategory;
import com.rmeunier.pizzeriaapp.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public Set<ProductCategory> getAllProductCategories() {
        return productCategoryService.getAllProductCategories();
    }

    @GetMapping("/{id}")
    public ProductCategory getProductCategory(@PathVariable("id") Long id) {
        return productCategoryService.getProductCategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductCategory(@RequestBody ProductCategory category) {
        if (productCategoryService.saveProductCategory(category)) {
            return ResponseEntity.ok("Product category saved.");
        } else {
            return ResponseEntity.ok("Product category was not saved.");
        }
    }
}
