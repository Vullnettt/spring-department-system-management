package com.example.productservice.repository;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, String> {
    Product findBySkuCode(String skuCode);
}
