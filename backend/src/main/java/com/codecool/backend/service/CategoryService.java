package com.codecool.backend.service;

import com.codecool.backend.dto.CategoryDto;
import com.codecool.backend.mapper.CategoryMapper;
import com.codecool.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service
@ConditionalOnProperty(name = "spring.datasource.url")
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDto> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .toList();
    }
}
