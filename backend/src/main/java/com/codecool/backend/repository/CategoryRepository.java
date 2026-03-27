package com.codecool.backend.repository;

import com.codecool.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    long count();
    Optional<Category> findByNameIgnoreCase(String name);
}
