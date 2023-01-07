package com.app.onemoretick.service.impl;

import com.app.onemoretick.models.Category;
import com.app.onemoretick.models.User;
import com.app.onemoretick.repository.CategoryRepository;
import com.app.onemoretick.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public Category updateCategory(Category category) {
        Category categoryFromDb = getById(category.getId());
        categoryFromDb.setName(category.getName());
        categoryFromDb.setTasks(category.getTasks());
        return categoryRepository.save(categoryFromDb);
    }

    @Override
    public void deleteUser(Integer id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }
}
