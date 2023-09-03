package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Category;
import com.example.WikiHow.repo.CategoriesRepo;

public interface CategoriesService {


    ApiResponse addCategory(Category category);
    ApiResponse getCategory();
    ApiResponse deleteCategory(Integer id);
    ApiResponse updateCategoryName(Integer id, String name);
}
