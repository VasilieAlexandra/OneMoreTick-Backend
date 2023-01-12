package com.app.onemoretick.controller;

import com.app.onemoretick.model.Category;
import com.app.onemoretick.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategory();
        if(categories!=null)
            return new ResponseEntity<>(categories, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(name = "category_id") Integer id){
        Category category = categoryService.getById(id);
        if(category!=null)
            return new ResponseEntity<>(category, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
