package com.codecool.backend.service;

import com.codecool.backend.dto.BrandDto;
import com.codecool.backend.model.Brand;
import com.codecool.backend.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BrandServiceTest {
    private BrandService brandService;
    private BrandRepository brandRepository;


    @BeforeEach
    void setup() {
        brandRepository = mock(BrandRepository.class);
        brandService = new BrandService(brandRepository);
    }

    @Test
    public void getAllBrand_returnMappedDto() {
        Brand b1 = new Brand("Mizo");
        Brand b2 = new Brand("Alpesi");

        when(brandRepository.findAll()).thenReturn(List.of(b1,b2));
        List<BrandDto> result = brandService.getAllBrands();

        assertEquals(2,result.size());
        assertEquals("Mizo", result.get(0).name());
        assertEquals("Alpesi", result.get(1).name());

    }

    @Test
    public void getAllBrand_returnEmptyList_WhenDbIsEmpty() {
        when(brandRepository.findAll()).thenReturn(List.of());

        List<BrandDto> results = brandService.getAllBrands();

        assertTrue(results.isEmpty());
        verify(brandRepository, times(1)).findAll();
    }
}
