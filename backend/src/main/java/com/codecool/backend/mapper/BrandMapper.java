package com.codecool.backend.mapper;

import com.codecool.backend.dto.BrandDto;
import com.codecool.backend.model.Brand;

public class BrandMapper {
    public static BrandDto toDto (Brand brand) {
        return new BrandDto(brand.getName());
    }
}
