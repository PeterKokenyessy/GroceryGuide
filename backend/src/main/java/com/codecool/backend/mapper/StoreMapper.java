package com.codecool.backend.mapper;

import com.codecool.backend.dto.StoreDto;
import com.codecool.backend.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public StoreDto toDto (Store store) {
        return new StoreDto(store.getName(), store.getColor());
    }
}
