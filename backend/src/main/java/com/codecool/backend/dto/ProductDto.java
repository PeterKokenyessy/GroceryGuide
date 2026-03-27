package com.codecool.backend.dto;

import java.util.List;

public record ProductDto(
        Long id,
        String name,
        Double quantity,
        String quantityUnit,
        String category,
        String brand,
        List<PriceDto> prices,
        String image_url
) {}


