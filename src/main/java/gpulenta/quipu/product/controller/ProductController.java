package gpulenta.quipu.product.controller;

import gpulenta.quipu.product.model.Product;
import gpulenta.quipu.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Find all products
    @Operation(summary = "Get all products", description = "Get all products details")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        if (productService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    // Find product by id
    @Operation(summary = "Get product by id", description = "Get a product details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        if (productService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    // Create product
    @Operation(summary = "Create product", description = "Create a new product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    // Update product
    @Operation(summary = "Update product by ID", description = "Update an existing product's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    // Delete product
    @Operation(summary = "Delete product", description = "Delete an existing product")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        productService.delete(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    // Find products by category
    @Operation(summary = "Get products by category", description = "Get all products details by category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        if (productService.getProductsByCategoryName(category).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(productService.getProductsByCategoryName(category), HttpStatus.OK);
    }
}



