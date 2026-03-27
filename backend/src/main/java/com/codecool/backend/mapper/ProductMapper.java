package com.codecool.backend.mapper;

import com.codecool.backend.dto.ProductDto;
import com.codecool.backend.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private PriceMapper priceMapper;

    public ProductMapper(PriceMapper priceMapper) {
        this.priceMapper = priceMapper;
    }

    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getQuantityUnit(),
                product.getCategory().getName(),
                product.getBrand().getName(),
                product.getPrices().stream().map(priceMapper::toDto).toList(),
                product.getImage_url()
        );
    }
}


