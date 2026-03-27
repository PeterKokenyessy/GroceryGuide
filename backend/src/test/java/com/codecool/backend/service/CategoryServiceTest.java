package com.codecool.backend.service;

import com.codecool.backend.dto.CategoryDto;
import com.codecool.backend.model.Category;
import com.codecool.backend.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    void setup(){
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void getAllCategories_returnsMappedDtos() {
        Category c1 = new Category("Dairy");
        Category c2 = new Category("Meat");

        when(categoryRepository.findAll()).thenReturn(List.of(c1, c2));
        List<CategoryDto> results = categoryService.getAllCategories();

        assertEquals(2, results.size());
        assertEquals("Dairy", results.get(0).name());
        assertEquals("Meat", results.get(1).name());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getAllCategories_returnsEmptyList_whenRepositoryIsEmpty(){
        when(categoryRepository.findAll()).thenReturn(List.of());

        List<CategoryDto> results = categoryService.getAllCategories();

        assertTrue(results.isEmpty());
        verify(categoryRepository, times(1)).findAll();
    }
}