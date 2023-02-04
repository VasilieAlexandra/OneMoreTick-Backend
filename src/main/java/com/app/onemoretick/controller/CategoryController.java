package com.app.onemoretick.controller;

import com.app.onemoretick.model.dto.CategoryDto;
import com.app.onemoretick.model.entity.Category;
import com.app.onemoretick.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<Category> categories = categoryService.getAllCategory();

        if(categories!=null){
            List<CategoryDto> categoriesDto = categories.stream()
                    .map(c->modelMapper.map(c, CategoryDto.class))
                    .toList();
            return new ResponseEntity<>(categoriesDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "category_id") Integer id){
        Category category = categoryService.getById(id);
        if(category != null){
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
