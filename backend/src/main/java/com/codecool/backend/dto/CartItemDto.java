package com.codecool.backend.dto;

import java.util.List;

public record CartItemDto(
        Long id,
        String name,
        Double quantity,
        String quantityUnit,
        Integer cartQuantity,
        String category,
        String brand,
        List<PriceDto> prices,
        Boolean checked,
        String imageUrl
) {
}
