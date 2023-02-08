package com.rmeunier.pizzeriaapp.service.impl;

import com.rmeunier.pizzeriaapp.error.ProductCategoryNotFoundException;
import com.rmeunier.pizzeriaapp.model.Product;
import com.rmeunier.pizzeriaapp.model.ProductCategory;
import com.rmeunier.pizzeriaapp.repo.ProductCategoryRepository;
import com.rmeunier.pizzeriaapp.repo.ProductRepository;
import com.rmeunier.pizzeriaapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public void saveProduct(Product product) throws ProductCategoryNotFoundException {
        Optional<ProductCategory> existingCategory = productCategoryRepository.findByName(product.getCategory().getName());

        if (existingCategory.isPresent()) {
            product.setCategory(existingCategory.get());
            productRepository.save(product);
        } else {
            throw new ProductCategoryNotFoundException();
        }
    }

    @Override
    public void updateProduct(Long id, Product product) {
        productCategoryRepository.findByName(product.getName()).ifPresent(productCategory -> {
            productRepository.findById(id).ifPresent(oldProduct -> {
                oldProduct.setName(product.getName());
                oldProduct.setDesc(product.getDesc());
                oldProduct.setPrice(product.getPrice());
                oldProduct.setCategory(productCategory);
                oldProduct.setModifiedAt(new Date().getTime());

                productRepository.save(oldProduct);
            });
        });
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresent(product -> {
            product.setDeletedAt(new Date().getTime());
        });
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.get();
    }

    @Override
    public Set<Product> getAllProducts() {
        return new HashSet<>(productRepository.findAll());
    }

    @Override
    public Set<Product> getProductsOfCategory(ProductCategory category) {
        return productRepository.findAll()
                .stream().filter(product -> {
                    return category.getName().equals(product.getCategory().getName());
                }).collect(Collectors.toSet());
    }
}
