package com.codecool.backend.service;

import com.codecool.backend.dto.CartDto;
import com.codecool.backend.model.*;
import com.codecool.backend.repository.CartItemRepository;
import com.codecool.backend.repository.CartRepository;
import com.codecool.backend.repository.ProductRepository;
import com.codecool.backend.model.User;
import com.codecool.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CartOptimizerService cartOptimizerService;

    private CartService cartService;

    @BeforeEach
    void setup() {

        cartRepository = mock(CartRepository.class);
        cartItemRepository = mock(CartItemRepository.class);
        productRepository = mock(ProductRepository.class);
        userRepository = mock(UserRepository.class);
        cartOptimizerService = mock(CartOptimizerService.class);

        cartService = new CartService(
                cartRepository,
                cartItemRepository,
                productRepository,
                userRepository,
                cartOptimizerService
        );
    }

    @Test
    void shouldIncreaseQuantityIfItemAlreadyExists() {

        CartItem existing = new CartItem();
        existing.setQuantity(2);

        when(cartItemRepository.findByCartUserUsernameAndProductId("user", 1L))
                .thenReturn(Optional.of(existing));

        cartService.addToCart("user", 1L, 3);

        assertEquals(5, existing.getQuantity());
        verify(cartItemRepository).save(existing);
    }

    @Test
    void shouldCreateCartIfUserHasNone() {

        Product product = new Product();
        product.setId(1L);

        User user = new User();
        user.setUsername("user");

        when(cartItemRepository.findByCartUserUsernameAndProductId("user", 1L))
                .thenReturn(Optional.empty());

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(cartRepository.findByUserUsername("user"))
                .thenReturn(Optional.empty());

        when(userRepository.findByUsername("user"))
                .thenReturn(Optional.of(user));


        when(cartRepository.save(any(Cart.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        cartService.addToCart("user", 1L, 2);

        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    void shouldDeleteCartItem() {

        cartService.deleteCartItem("user", 1L);

        verify(cartItemRepository)
                .deleteByCartUserUsernameAndProductId("user", 1L);
    }

    @Test
    void shouldUpdateQuantity() {

        CartItem item = new CartItem();
        item.setQuantity(2);

        when(cartItemRepository.findByCartUserUsernameAndProductId("user", 1L))
                .thenReturn(Optional.of(item));

        cartService.updateQuantity("user", 1L, 5);

        assertEquals(5, item.getQuantity());
    }

    @Test
    void shouldReturnOptimizedCart() {

        Cart cart = new Cart();
        CartDto dto = new CartDto(1000.0, 0.0, null);

        when(cartRepository.findByUserUsername("user"))
                .thenReturn(Optional.of(cart));

        when(cartOptimizerService.optimizeCart(cart))
                .thenReturn(dto);

        CartDto result = cartService.getUserCart("user");

        assertEquals(1000.0, result.totalPrice());
        verify(cartOptimizerService).optimizeCart(cart);
    }
}