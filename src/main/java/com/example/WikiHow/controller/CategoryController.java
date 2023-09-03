package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Category;
import com.example.WikiHow.service.impl.CategoriesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoriesServiceImpl categoriesService;

    public HttpEntity<ApiResponse> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoriesService.addCategory(category));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        return ResponseEntity.ok(categoriesService.deleteCategory(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> updateCategoryName(@PathVariable Integer id,
                                                      @RequestParam("name") String name){
        return ResponseEntity.ok(categoriesService.updateCategoryName(id, name));
    }

    @GetMapping
    public HttpEntity<ApiResponse> getCategories(){
        return ResponseEntity.ok(categoriesService.getCategory());
    }


}
