package com.codecool.backend.service;

import com.codecool.backend.dto.BrandDto;
import com.codecool.backend.mapper.BrandMapper;
import com.codecool.backend.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service
@ConditionalOnProperty(name = "spring.datasource.url")
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDto> getAllBrands () {
       return brandRepository.findAll()
                .stream()
                .map(BrandMapper::toDto)
                .toList();
    }
}
