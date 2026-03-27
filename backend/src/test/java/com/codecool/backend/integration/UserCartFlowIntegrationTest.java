package com.codecool.backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.codecool.backend.dto.requestDto.QuantityDto;
import com.codecool.backend.repository.BrandRepository;
import com.codecool.backend.repository.CategoryRepository;
import com.codecool.backend.repository.ProductRepository;
import com.codecool.backend.repository.StoreRepository;

import org.springframework.http.MediaType;
import com.codecool.backend.dto.RegistrationRequest;
import com.codecool.backend.model.Brand;
import com.codecool.backend.model.Price;
import com.codecool.backend.model.Product;
import com.codecool.backend.model.Store;
import com.codecool.backend.model.Category;

import com.codecool.backend.dto.LoginRequest;
import com.codecool.backend.dto.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("int_test")
public class UserCartFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BrandRepository brandRepository;


    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    private Long id;

    @BeforeEach
    void setup() {

        Brand brand = brandRepository.save(new Brand("TestBrand"));
        Category category = categoryRepository.save(new Category("TestCategory"));

        Product product = new Product();
        product.setName("Milk");
        product.setBrand(brand);
        product.setCategory(category);
        product.setQuantity(1.0);
        product.setQuantityUnit("L");

        Store store = new Store();
        store.setName("Tesco");
        store.setColor("bg-red-500");
        store = storeRepository.save(store);

        Price price = new Price();
        price.setPrice(300.0);
        price.setProduct(product);
        price.setStore(store);
        product.setPrices(List.of(price));
        id = productRepository.save(product).getId();


    }


    @Test
    void user_full_flow_should_work() throws Exception {

        // ---------- 1. REGISTER ----------
        RegistrationRequest registerRequest = new RegistrationRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setPassword("password");
        registerRequest.setEmail("test@example.com");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk());

        // ---------- 2. LOGIN ----------
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password");

        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        AuthResponse authResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                AuthResponse.class
        );

        String token = authResponse.getAccessToken();

        // ---------- 3. ADD TO CART ----------
        QuantityDto addRequest = new QuantityDto(2);

        mockMvc.perform(post("/api/cart/items/" + id.toString())
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addRequest)))
                .andExpect(status().isOk());

        // ---------- 4. GET CART ----------
        mockMvc.perform(get("/api/cart")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cart[0].items[0].id").exists())
                .andExpect(jsonPath("$.cart[0].items[0].cartQuantity").value(2));
    }



}
