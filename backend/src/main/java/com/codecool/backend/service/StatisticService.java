package com.codecool.backend.service;

import com.codecool.backend.dto.BasicStatDto;
import com.codecool.backend.exception.ResourceNotFoundException;
import com.codecool.backend.repository.ProductRepository;
import com.codecool.backend.repository.StoreRepository;
import org.springframework.stereotype.Service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service
@ConditionalOnProperty(name = "spring.datasource.url")
public class StatisticService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;


    public StatisticService(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public BasicStatDto getProductsNumber() {
        try {
            Long productCount = productRepository.count();
            Long storeCount = storeRepository.count();

            return new BasicStatDto(productCount,storeCount);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Can't get product count");
        }
    }
}
