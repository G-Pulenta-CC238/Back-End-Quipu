package gpulenta.quipu.product.service.impl;

import gpulenta.quipu.product.model.Product;
import gpulenta.quipu.product.repository.ProductRepository;
import gpulenta.quipu.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = productRepository.findById(product.getId()).orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setProductDescription(product.getProductDescription());
            productToUpdate.setProductPrice(product.getProductPrice());
            productToUpdate.setProductStock(product.getProductStock());
            productToUpdate.setProductImageUrl(product.getProductImageUrl());
            productToUpdate.setProductRating(product.getProductRating());
            productToUpdate.setCategory(product.getCategory());
            return productRepository.save(productToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        if (categoryName != null) {
            return productRepository.findByCategory_CategoryName(categoryName);
        } else {
            return null;
        }
    }

}
