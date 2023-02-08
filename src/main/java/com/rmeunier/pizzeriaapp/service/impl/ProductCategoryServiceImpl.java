package com.rmeunier.pizzeriaapp.service.impl;

import com.rmeunier.pizzeriaapp.model.ProductCategory;
import com.rmeunier.pizzeriaapp.repo.ProductCategoryRepository;
import com.rmeunier.pizzeriaapp.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public boolean saveProductCategory(ProductCategory category) {
        if (!productCategoryRepository.findByName(category.getName()).isPresent()) {
            productCategoryRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public void updateProductCategory(Long id, ProductCategory category) {
        productCategoryRepository.findById(id).ifPresent(oldCategory -> {
            oldCategory.setName(category.getName());
            oldCategory.setDesc(category.getDesc());
            oldCategory.setModifiedAt(new Date().getTime());
            productCategoryRepository.save(oldCategory);
        });
    }

    @Override
    public void deleteProductCategory(Long id) {
        productCategoryRepository.findById(id).ifPresent(category -> {
            category.setDeletedAt(new Date().getTime());
        });
    }

    @Override
    public ProductCategory getProductCategory(Long id) {
        Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(id);
        return optionalCategory.get();
    }

    @Override
    public ProductCategory getProductCategoryByName(String name) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findByName(name);
        return optionalProductCategory.get();
    }

    @Override
    public Set<ProductCategory> getAllProductCategories() {
        return new HashSet<>(productCategoryRepository.findAll());
    }
}
