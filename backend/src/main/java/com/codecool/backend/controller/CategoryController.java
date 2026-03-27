package com.codecool.backend.controller;

import com.codecool.backend.dto.CategoryDto;
import com.codecool.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations related to categories")
public class CategoryController {
    private final Optional<CategoryService> categoryService;

    @GetMapping
    @Operation(summary = "Get all categories")
    public List<CategoryDto> getAllCategories() {
        return getService().getAllCategories();
    }

    private CategoryService getService() {
        return categoryService.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Database not available"));
    }
}
