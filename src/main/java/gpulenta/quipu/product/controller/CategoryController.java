package gpulenta.quipu.product.controller;

import gpulenta.quipu.product.model.Category;
import gpulenta.quipu.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all categories", description = "Get all categories details")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        if (categoryService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create category", description = "Create a new category")
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @Operation(summary = "Get category by name", description = "Get a category details by name")
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryName) {
        if (categoryService.getCategoryByCategoryName(categoryName) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(categoryService.getCategoryByCategoryName(categoryName), HttpStatus.OK);
    }

    @Operation(summary = "Update category by ID", description = "Update an existing category's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        if (categoryService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        category.setId(id);
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @Operation(summary = "Delete category", description = "Delete an existing category")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        if (categoryService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        categoryService.delete(id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
