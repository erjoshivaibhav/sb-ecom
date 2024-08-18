package com.ecom.sb_ecom.controller;

import com.ecom.sb_ecom.model.Category;
import com.ecom.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/api/admin/category")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category created..!!");
    }

    @GetMapping("/api/public/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
    }

    @DeleteMapping("/api/admin/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {

            String status = categoryService.deleteCategory(id);
            return new ResponseEntity<>(status, HttpStatus.OK);

    }

    @PutMapping("/api/admin/category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id,@RequestBody Category category){

            String status = categoryService.updateCategory(id, category);
            return ResponseEntity.status(HttpStatus.OK).body(status);

    }

}

