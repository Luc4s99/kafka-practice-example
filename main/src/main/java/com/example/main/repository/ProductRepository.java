package com.example.main.repository;

import com.example.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value="SELECT * FROM (SELECT * FROM Product ORDER BY dbms_random.value) WHERE rownum = 1", nativeQuery = true)
    Product randomProduct();
}
