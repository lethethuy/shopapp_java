package com.example.shopapp.service;

import com.example.shopapp.DTO.CategoryDTO;
import com.example.shopapp.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(long categoryId, CategoryDTO category);

    void deleteCategory(long id);
}
