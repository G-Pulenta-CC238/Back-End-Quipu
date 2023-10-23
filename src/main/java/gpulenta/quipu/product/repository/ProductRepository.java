package gpulenta.quipu.product.repository;


import gpulenta.quipu.product.model.Category;
import gpulenta.quipu.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    Product findByProductName(String productName);

    List<Product> findByCategory_CategoryName(String categoryName);
}
