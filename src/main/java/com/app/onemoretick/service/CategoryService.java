package com.app.onemoretick.service;

import com.app.onemoretick.models.Category;

public interface CategoryService {
    Category addCategory(Category category);
    Category getById(Integer id);
    Category updateCategory(Category category);
    void deleteUser(Integer id);

}
