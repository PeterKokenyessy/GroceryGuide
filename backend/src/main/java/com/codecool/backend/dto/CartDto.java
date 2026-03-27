package com.codecool.backend.dto;

import java.util.List;

public record CartDto(Double totalPrice, Double savedMoney, List<CartStoresDto> cart) {
}
