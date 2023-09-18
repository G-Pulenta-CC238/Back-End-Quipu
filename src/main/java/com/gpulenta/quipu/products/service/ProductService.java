package com.gpulenta.quipu.products.service;

import com.gpulenta.quipu.products.model.Product;
import com.gpulenta.quipu.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setProductDescription(product.getProductDescription());
            productToUpdate.setProductPrice(product.getProductPrice());
            productToUpdate.setProductImageUrl(product.getProductImageUrl());
            productToUpdate.setProductRating(product.getProductRating());
            return productRepository.save(productToUpdate);
        } else {
            return null;
        }
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
    public Product getProductByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }
    public Product getProductByCategory(String productCategory) {
        return productRepository.findByProductCategory(productCategory);
    }
    public Product getProductByRating(Double productRating) {
        return productRepository.findByProductRating(productRating);
    }
}
