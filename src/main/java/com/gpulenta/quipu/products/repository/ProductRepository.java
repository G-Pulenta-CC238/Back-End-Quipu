package com.gpulenta.quipu.products.repository;

import com.gpulenta.quipu.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductName(String productName);
    Product findByProductCategory(String productCategory);
    Product findByProductRating(Double productRating);
}
