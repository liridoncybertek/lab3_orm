package com.cybertek.lab3_orm.service;

import com.cybertek.lab3_orm.model.Category;
import com.cybertek.lab3_orm.repository.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> readAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Category readById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category createOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
