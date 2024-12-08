package com.ecommerce.service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
    	return null;
    }

    public Category updateCategory(Integer categoryId, Category category) {
    	return null;
    }

    public void deleteCategory(Integer categoryId) {
        
    }

    public List<Category> getAllCategories() {
    	return null;
    }

    public Category getCategoryById(Integer categoryId) {
    	return null;
    }
}
