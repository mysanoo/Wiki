package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Category;
import com.example.WikiHow.repo.CategoriesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriesServiceImplTest {

    private CategoriesServiceImpl categoriesService;
    @Mock
    private CategoriesRepo categoriesRepo;

    @BeforeEach
    void setUp() {
        categoriesService = new CategoriesServiceImpl(categoriesRepo);
    }

    @Test
    void getCategory() {
        //given
        List<Category> categories = Arrays.asList(Category.builder().name("fruits").build(),
                Category.builder().name("vegetables").build());
        //when
        when(categoriesRepo.findAll()).thenReturn(categories);

        ApiResponse result = categoriesService.getCategory();
        //then
        org.mockito.Mockito.verify(categoriesRepo).findAll();

        assertEquals(categories, result.getData());
    }

    @Test
    void addCategory() {
        //given

        Category category = Category.builder()
                .name("Fruits")
                .build();

        //when
        categoriesService.addCategory(category);
        //then
        ArgumentCaptor<Category> argumentCaptor = ArgumentCaptor.forClass(Category.class);
        org.mockito.Mockito.verify(categoriesRepo).save(argumentCaptor.capture());
        Category capturedCategory = argumentCaptor.getValue();
        org.assertj.core.api.AssertionsForClassTypes.assertThat(capturedCategory).isEqualTo(category);

    }

    @Test
    void deleteCategory() {
        Category mockCategory = new Category();
        mockCategory.setId(1);
        // Other properties can be set accordingly

        // Configure mock behavior
        when(categoriesRepo.findById(1)).thenReturn(Optional.of(mockCategory));

        // Call the method under test
        ApiResponse result = categoriesService.deleteCategory(1);

        // Verify the correct methods were called
        org.mockito.Mockito.verify(categoriesRepo, times(1)).findById(1);
        org.mockito.Mockito.verify(categoriesRepo, times(1)).delete(mockCategory);

        // Assert the result
        assertEquals(mockCategory, result.getData());
    }

    @Test
    void updateCategoryName() {
        /*Category category = Category.builder()
                .id(1)
                .name("Fruits")
                .build();

        when(categoriesRepo.findById(category.getId())).thenReturn(Optional.of(category));

        ApiResponse result = categoriesService.updateCategoryName(1, "Vegetables");
        org.mockito.Mockito.verify(categoriesRepo, categoriesService.updateCategoryName())*/


        Category mockCategory = new Category();
        mockCategory.setId(1);
        mockCategory.setName("Fruits");
        // Other properties can be set accordingly

        // Configure mock behavior
        when(categoriesRepo.findById(1)).thenReturn(Optional.of(mockCategory));

        // Call the method under test
        ApiResponse result = categoriesService.updateCategoryName(1, "Vegetables");

        // Verify the correct methods were called
        org.mockito.Mockito.verify(categoriesRepo, times(1)).findById(1);
        org.mockito.Mockito.verify(categoriesRepo, times(1)).save(mockCategory);

        // Assert the result
        assertEquals("Vegetables", mockCategory.getName());
        assertEquals(mockCategory, result.getData());

    }
}