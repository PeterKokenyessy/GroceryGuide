package com.codecool.backend.service;

import com.codecool.backend.dto.PriceDto;
import com.codecool.backend.dto.ProductDto;
import com.codecool.backend.mapper.ProductMapper;
import com.codecool.backend.model.Product;
import com.codecool.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductMapper mapper;
    private ProductService service;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        mapper = mock(ProductMapper.class);

        service = new ProductService(productRepository, mapper);
    }

    @Test
    void getAllProducts_givenTwoProducts_returnsTwoDtos() {
        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Product> page = new PageImpl<>(List.of(p1, p2));

        when(productRepository.findAll(any(Pageable.class))).thenReturn(page);

        ProductDto dto1 = new ProductDto(1L, "Apple", 1.0, "kg", "Fruit", "BrandA",List.of(),"");
        ProductDto dto2 = new ProductDto(2L, "Pear", 2.0, "kg", "Fruit", "BrandB",List.of(),"");

        when(mapper.toDto(p1)).thenReturn(dto1);
        when(mapper.toDto(p2)).thenReturn(dto2);

        List<ProductDto> result = service.getAllProducts(pageable).getContent();

        assertEquals(2, result.size());
        assertEquals("Apple", result.get(0).name());
        assertEquals("Pear", result.get(1).name());
    }

    @Test
    void getAllProducts_returnsEmptyList_whenRepositoryReturnsEmpty() {

        Page<Product> page = new PageImpl<>(List.of());
        Pageable pageable = PageRequest.of(0, 10);

        when(productRepository.findAll(any(Pageable.class))).thenReturn(page);

        List<ProductDto> result = service.getAllProducts(pageable).getContent();

        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll(pageable);
        verify(mapper, never()).toDto(any());
    }

    @Test
    void searchProducts_filtersByCategoryOnly() {

        Product p1 = mock(Product.class);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(p1));

        when(productRepository.findAll(
                ArgumentMatchers.<Specification<Product>>any(),
                any(Pageable.class)
        )).thenReturn(page);

        ProductDto dto1 = new ProductDto(
                1L, "Milk", 1.0, "l", "Dairy", "Mizo", List.of(), ""
        );

        when(mapper.toDto(p1)).thenReturn(dto1);

        List<ProductDto> result =
                service.searchProducts("", "dairy", List.of(), pageable).getContent();

        assertEquals(1, result.size());
        assertEquals("Milk", result.get(0).name());

        verify(productRepository, times(1))
                .findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void searchProducts_filtersBySingleBrand() {

        Product p1 = mock(Product.class);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(p1));

        when(productRepository.findAll(
                any(Specification.class),
                any(Pageable.class)
        )).thenReturn(page);

        ProductDto dto1 = new ProductDto(
                1L, "Milk", 1.0, "l", "Dairy", "Mizo", List.of(), ""
        );

        when(mapper.toDto(p1)).thenReturn(dto1);

        List<ProductDto> result =
                service.searchProducts("", "dairy", List.of("Mizo"), pageable)
                        .getContent();

        assertEquals(1, result.size());
        assertEquals("Milk", result.get(0).name());

        verify(productRepository, times(1))
                .findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void searchProducts_filtersByMultipleBrands() {

        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(p1, p2));

        when(productRepository.findAll(
                any(Specification.class),
                any(Pageable.class)
        )).thenReturn(page);

        ProductDto dto1 = new ProductDto(1L, "Milk", 1.0, "l", "Dairy", "Mizo", List.of(), "");
        ProductDto dto2 = new ProductDto(2L, "Chocolate Milk", 1.0, "l", "Dairy", "Milka", List.of(), "");

        when(mapper.toDto(p1)).thenReturn(dto1);
        when(mapper.toDto(p2)).thenReturn(dto2);

        List<ProductDto> result =
                service.searchProducts("", "dairy", List.of("Mizo", "Milka"), pageable)
                        .getContent();

        assertEquals(2, result.size());
        assertEquals("Milk", result.get(0).name());
        assertEquals("Chocolate Milk", result.get(1).name());

        verify(productRepository, times(1))
                .findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void searchProducts_searchByProductName() {

        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(p1, p2));

        when(productRepository.findAll(
                any(Specification.class),
                any(Pageable.class)
        )).thenReturn(page);

        ProductDto dto1 = new ProductDto(1L, "Milk", 1.0, "l", "Dairy", "Mizo", List.of(), "");
        ProductDto dto2 = new ProductDto(2L, "Chocolate Milk", 1.0, "l", "Dairy", "Milka", List.of(), "");

        when(mapper.toDto(p1)).thenReturn(dto1);
        when(mapper.toDto(p2)).thenReturn(dto2);

        List<ProductDto> result =
                service.searchProducts("milk", "", List.of(), pageable)
                        .getContent();

        assertEquals(2, result.size());
        assertEquals("Milk", result.get(0).name());
        assertEquals("Chocolate Milk", result.get(1).name());

        verify(productRepository, times(1))
                .findAll(any(Specification.class), any(Pageable.class));
    }
}