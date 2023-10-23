package gpulenta.quipu.product.service;

import gpulenta.quipu.product.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category);

    void delete(Long id);

    Category getCategoryByCategoryName(String categoryName);
}
