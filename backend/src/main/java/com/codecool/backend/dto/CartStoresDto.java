package com.codecool.backend.dto;

import java.util.List;

public record CartStoresDto(String storeName, Double storeTotal, String color ,List<CartItemDto> items) {
}
