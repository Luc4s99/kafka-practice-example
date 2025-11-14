package com.example.main.service;

import com.example.main.model.Product;
import org.springframework.stereotype.Service;
import com.example.main.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {

    private ProductRepository productRepository;

    public MenuService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Transactional
    public Product save(Product p) {

        return productRepository.save(p);
    }
}
