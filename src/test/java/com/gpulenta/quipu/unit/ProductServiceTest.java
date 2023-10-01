package com.gpulenta.quipu.unit;

import com.gpulenta.quipu.products.controller.ProductController;
import com.gpulenta.quipu.products.model.Product;
import com.gpulenta.quipu.products.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    @Test
    void testGetAllProducts() {
        ProductService productService = Mockito.mock(ProductService.class);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", "Description 1", 10.0, "image1.jpg", 4.5, "Category 1"));
        productList.add(new Product(2, "Product 2", "Description 2", 20.0, "image2.jpg", 3.8, "Category 2"));

        when(productService.getAllProducts()).thenReturn(productList);

        ProductController productController = new ProductController(productService);

        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
    }

    @Test
    void testCreateProduct() {
        ProductService productService = Mockito.mock(ProductService.class);

        Product product = new Product(1, "Product 1", "Description 1", 10.0, "image1.jpg", 4.5, "Category 1");

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        ProductController productController = new ProductController(productService);

        ResponseEntity<Product> responseEntity = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

}
