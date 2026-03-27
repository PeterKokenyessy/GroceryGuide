package com.codecool.backend.mapper;

import com.codecool.backend.dto.CategoryDto;
import com.codecool.backend.model.Category;

public class CategoryMapper {
    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
