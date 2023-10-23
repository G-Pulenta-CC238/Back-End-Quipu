package gpulenta.quipu.product.service.impl;

import gpulenta.quipu.product.model.Category;
import gpulenta.quipu.product.repository.CategoryRepository;
import gpulenta.quipu.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category categoryToUpdate = categoryRepository.findById(category.getId()).orElseThrow(() -> new RuntimeException("Category not found"));
        if (categoryToUpdate != null) {
            categoryToUpdate.setCategoryName(category.getCategoryName());
            categoryToUpdate.setCategoryImageUrl(category.getCategoryImageUrl());
            return categoryRepository.save(categoryToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
}
