package com.ecom.sb_ecom.service;

import com.ecom.sb_ecom.exceptionHandler.CategoryAlreadyExistException;
import com.ecom.sb_ecom.exceptionHandler.ResourceNotFoundException;
import com.ecom.sb_ecom.model.Category;
import com.ecom.sb_ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(!existingCategory.isPresent()){
            categoryRepository.save(category);
        }
        else throw new CategoryAlreadyExistException("Category already exists..!!");

    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public String deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            categoryRepository.delete(optionalCategory.get());
            return "Category with ID : "+id+" deleted..!!";
        }

        else throw new ResourceNotFoundException("Resource not found");


    }

    @Override
    public String updateCategory(Long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory.isPresent()){

            throw new CategoryAlreadyExistException("Category already exists..!!");
        }

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){

            category.setId(optionalCategory.get().getId());
            categoryRepository.save(category);
            return "Category with ID : "+id+" updated..!!";
        }

        else throw new ResourceNotFoundException("Resource not found");
    }
}
