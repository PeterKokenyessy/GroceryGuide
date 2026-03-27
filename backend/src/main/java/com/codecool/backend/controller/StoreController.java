package com.codecool.backend.controller;

import com.codecool.backend.dto.StoreDto;
import com.codecool.backend.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final Optional<StoreService> storeService;

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStore () {
        List<StoreDto> dto = getService().getAllStore();
        return ResponseEntity.ok(dto);
    }

    private StoreService getService() {
        return storeService.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Database not available"));
    }
}
