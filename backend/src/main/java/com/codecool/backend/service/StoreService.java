package com.codecool.backend.service;

import com.codecool.backend.dto.StoreDto;
import com.codecool.backend.exception.ResourceNotFoundException;
import com.codecool.backend.mapper.StoreMapper;
import com.codecool.backend.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service
@ConditionalOnProperty(name = "spring.datasource.url")
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    public List<StoreDto> getAllStore() {
        try {
            return storeRepository.findAll().stream().map(storeMapper::toDto).toList();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("There is no stores");
        }
    }
}
