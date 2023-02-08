package com.rmeunier.pizzeriaapp.rest;

import com.rmeunier.pizzeriaapp.error.ProductCategoryNotFoundException;
import com.rmeunier.pizzeriaapp.model.Product;
import com.rmeunier.pizzeriaapp.model.ProductCategory;
import com.rmeunier.pizzeriaapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Set<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/pizzas")
    public Set<Product> getPizzas() {
        return productService.getProductsOfCategory(new ProductCategory("PIZZA"));
    }

    @GetMapping("/salads")
    public Set<Product> getSalads() {
        return productService.getProductsOfCategory(new ProductCategory("SALAD"));
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return ResponseEntity.ok("Product successfully added.");
        } catch (ProductCategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not add product: category doesn't exist!");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> modifyProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok("Product successfully added.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Successfully deleted product.");
    }

}
