package com.codecool.backend.service;

import com.codecool.backend.dto.ProductDto;
import com.codecool.backend.mapper.ProductMapper;
import com.codecool.backend.model.Product;
import com.codecool.backend.repository.ProductRepository;
import com.codecool.backend.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.transaction.annotation.Transactional;

@Service
@ConditionalOnProperty(name = "spring.datasource.url")
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> searchProducts(String productName, String categoryName, List<String> brands,Pageable pageable) {

        Specification<Product> spec = Specification.where(null);

        if(productName != null && !productName.isBlank()){
            spec = spec.and(ProductSpecification.hasName(productName));
        }

        if (categoryName != null && !categoryName.isBlank()) {
            spec = spec.and(ProductSpecification.hasCategory(categoryName));
        }

        if (brands != null && !brands.isEmpty()) {
            spec = spec.and(ProductSpecification.hasBrands(brands));
        }

        return productRepository.findAll(spec,pageable)
                .map(productMapper::toDto);
    }
}

