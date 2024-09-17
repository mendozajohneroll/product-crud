package com.erollmendoza.product_crud.repository;

import com.erollmendoza.product_crud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);
    Optional<Product> findByProductDescription(String productDescription);
    Optional<Product> findByProductType(String productType);
}
