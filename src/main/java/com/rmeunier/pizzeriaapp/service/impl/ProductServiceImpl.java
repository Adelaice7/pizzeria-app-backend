package com.rmeunier.pizzeriaapp.service.impl;

import com.rmeunier.pizzeriaapp.model.Product;
import com.rmeunier.pizzeriaapp.repo.ProductRepository;
import com.rmeunier.pizzeriaapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        productRepository.findById(id).ifPresent(oldProduct -> {
            oldProduct.setName(product.getName());
            oldProduct.setDesc(product.getDesc());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setCartItem(product.getCartItem());
            oldProduct.setOrderItems(product.getOrderItems());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setModifiedAt(new Date().getTime());

            productRepository.save(oldProduct);
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
}
