package com.codecool.backend.controller;

import com.codecool.backend.dto.BasicStatDto;
import com.codecool.backend.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/stat")
@RequiredArgsConstructor
public class StatisticController {

    private final Optional<StatisticService> statisticService;

    @GetMapping("/basic")
    public ResponseEntity<BasicStatDto> getProductCount () {
        BasicStatDto dto = getService().getProductsNumber();
        return ResponseEntity.ok(dto);
    }

    private StatisticService getService() {
        return statisticService.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Database not available"));
    }
}
