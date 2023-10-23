package gpulenta.quipu.product.service;

import gpulenta.quipu.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Product product);

    void delete(Long id);

    List<Product> getProductsByCategoryName(String categoryName);

}
