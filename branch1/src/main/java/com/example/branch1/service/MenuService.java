package com.example.branch1.service;

import com.example.branch1.model.Product;
import com.example.branch1.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {

    ProductRepository productRepository;

    public MenuService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Transactional
    public Product save(Product p) {

        return productRepository.save(p);
    }

    @Transactional
    public void delete(Product p) {

        productRepository.delete(p);
    }
}
