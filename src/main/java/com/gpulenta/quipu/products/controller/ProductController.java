package com.gpulenta.quipu.products.controller;

import com.gpulenta.quipu.products.model.Product;
import com.gpulenta.quipu.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import com.gpulenta.quipu.shared.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products", description = "Get all products details by their id")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @Operation(summary = "Get a product by id", description = "Get a product details by their id")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
    @Operation(summary = "Create a new product", description = "Create a new product with the provided information")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        validateProduct(product);
        existsProductByProductName(product);
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }
    @Operation(summary = "Update product by ID", description = "Update an existing product's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        validateProduct(product);
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @Operation(summary = "Delete product by ID", description = "Delete a product by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id: " + id + " was deleted", HttpStatus.OK);
    }

    public void validateProduct(Product product) {
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new ValidationException("Product name is required");
        }
        if (product.getProductDescription() == null || product.getProductDescription().trim().isEmpty()) {
            throw new ValidationException("Product description is required");
        }
        if (product.getProductPrice() == null || product.getProductPrice() <= 0) {
            throw new ValidationException("Product price is required");
        }
    }

    private void existsProductByProductName(Product product) {
        if (productService.getProductByProductName(product.getProductName()) != null) {
            throw new ValidationException("Product name already exists");
        }
    }
}