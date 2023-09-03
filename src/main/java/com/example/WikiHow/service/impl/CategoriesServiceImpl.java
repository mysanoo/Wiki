package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Category;
import com.example.WikiHow.repo.CategoriesRepo;
import com.example.WikiHow.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {


    private final CategoriesRepo categoriesRepo;
    @Override
    public ApiResponse getCategory() {
        return ApiResponse.builder()
                .data(categoriesRepo.findAll())
                .build();
    }

    @Override
    public ApiResponse addCategory(Category category) {
        Category newCategory = Category.builder()
                .name(category.getName())
                .build();
        categoriesRepo.save(category);
        return ApiResponse.builder()
                .data(newCategory)
                .build();
    }

    @Override
    public ApiResponse deleteCategory(Integer id) {
        Category category = categoriesRepo.findById(id).stream().findFirst().orElse(null);
        assert category != null;
        categoriesRepo.delete(category);
        return ApiResponse.builder()
                .data(category)
                .build();
    }

    @Override
    public ApiResponse updateCategoryName(Integer id, String name) {
        Category category = categoriesRepo.findById(id).stream().findFirst().orElse(null);
        assert category != null;
        category.setName(name);
        categoriesRepo.save(category);
        return ApiResponse.builder()
                .data(category)
                .build();
    }
}
