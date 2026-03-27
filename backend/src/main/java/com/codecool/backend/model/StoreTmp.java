package com.codecool.backend.model;

import com.codecool.backend.dto.CartItemDto;

import java.util.ArrayList;
import java.util.List;

public class StoreTmp {

    public String storeName;
    public List<CartItemDto> items;
    public double storeTotal;

    public StoreTmp(String storeName) {
        this.storeName = storeName;
        this.items = new ArrayList<>();
        this.storeTotal = 0;
    }

    public String getStoreName() {
        return storeName;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public double getStoreTotal() {
        return storeTotal;
    }
}