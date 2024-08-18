package com.ecom.sb_ecom.service;

import com.ecom.sb_ecom.model.Category;

import java.util.List;

public interface CategoryService {


    public void addCategory(Category category);

    public List<Category> getAllCategory();

    String deleteCategory(Long id);

    String updateCategory(Long id, Category category);
}



