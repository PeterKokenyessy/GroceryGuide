package com.codecool.backend.mapper;

import com.codecool.backend.dto.CartItemDto;
import com.codecool.backend.model.CartItem;
import com.codecool.backend.model.Product;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    private PriceMapper priceMapper;

    public CartItemMapper(PriceMapper priceMapper) {
        this.priceMapper = priceMapper;
    }

    public CartItemDto toDto(CartItem cartItem) {
        Product product = cartItem.getProduct();
        return new CartItemDto(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getQuantityUnit(),
                cartItem.getQuantity(),
                product.getCategory().getName(),
                product.getBrand().getName(),
                product.getPrices().stream().map(priceMapper::toDto).toList(),
                cartItem.isChecked(),
                product.getImage_url()

        );
    }
}
