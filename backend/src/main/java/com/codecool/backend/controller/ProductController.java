package com.codecool.backend.controller;

import com.codecool.backend.dto.ProductDto;
import com.codecool.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@CrossOrigin(origins = "https://tesco-frontend-latest.onrender.com")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {
    private final Optional<ProductService> productService;

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @PageableDefault(size = 20, page = 0) Pageable pageable
    ) {
        Page<ProductDto> page = getService().getAllProducts(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    @Operation(summary = "Search products with optional filters")
    public ResponseEntity<Page<ProductDto>> searchProducts(
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) List<String> brands,
            @PageableDefault(size = 20, page = 0) Pageable pageable

    ) {

        Page<ProductDto> page = getService().searchProducts(product, category, brands,pageable);
        return ResponseEntity.ok(page);
    }

    private ProductService getService() {
        return productService.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Database not available"));
    }

}