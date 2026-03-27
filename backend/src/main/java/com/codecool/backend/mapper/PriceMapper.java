package com.codecool.backend.mapper;

import com.codecool.backend.dto.PriceDto;
import com.codecool.backend.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper  {
    public PriceDto toDto (Price price) {
        return new PriceDto(price.getPrice(),price.getStore().getName(),price.getStore().getColor());
    }
}
