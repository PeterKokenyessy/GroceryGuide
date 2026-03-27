package com.codecool.backend.controller;

import com.codecool.backend.dto.BrandDto;

import com.codecool.backend.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "Brands", description = "Operations related to brands")
public class BrandController {

    private final Optional<BrandService> brandService;

    @GetMapping
    @Operation(summary = "Get all brands")
    public List<BrandDto> getAllBrand () {
        return getService().getAllBrands();
    }

    private BrandService getService() {
        return brandService.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Database not available"));
    }
}
