package com.codecool.backend.service;

import com.codecool.backend.dto.CartDto;
import com.codecool.backend.exception.InvalidPermissionException;
import com.codecool.backend.exception.BadRequestException;
import com.codecool.backend.exception.ResourceNotFoundException;
import com.codecool.backend.model.*;
import com.codecool.backend.repository.CartItemRepository;
import com.codecool.backend.repository.CartRepository;
import com.codecool.backend.repository.ProductRepository;
import com.codecool.backend.model.User;
import com.codecool.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service
@Transactional
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.datasource.url")
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartOptimizerService cartOptimizerService;

    public void addToCart(String username, Long productId, Integer quantity) {

        if (quantity < 1) {
            throw new BadRequestException("Quantity must be positive");
        }

        Optional<CartItem> existingItem =
                cartItemRepository.findByCartUserUsernameAndProductId(username, productId);

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);

            cartItemRepository.save(item);

        } else {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new BadRequestException("Product not found"));

            Cart cart = cartRepository.findByUserUsername(username)
                    .orElseGet(() -> {
                        User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new InvalidPermissionException("User not found"));

                        Cart newCart = new Cart(user);
                        return cartRepository.save(newCart);
                    });

            CartItem item = new CartItem(cart, product, quantity);
            cart.getItems().add(item);
            cartItemRepository.save(item);
        }
    }

    public void deleteCartItem(String username, Long productId) {
        cartItemRepository.deleteByCartUserUsernameAndProductId(username, productId);
    }

    public void updateQuantity(String username, Long productId, Integer quantity) {
        if (quantity < 1) {
            throw new BadRequestException("Quantity must be positive");
        }
        CartItem item = cartItemRepository.findByCartUserUsernameAndProductId(username, productId).orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        item.setQuantity(quantity);
    }

    public CartDto getUserCart(String username) {
        Cart cart = cartRepository.findByUserUsername(username)
                .orElseGet(() -> {
                    User user = userRepository.findByUsername(username)
                            .orElseThrow(() -> new InvalidPermissionException("Access denied"));
                    return new Cart(user);
                });

        return cartOptimizerService.optimizeCart(cart);
    }

    public void updateChecked(String name, Long productId, Boolean checked) {
        CartItem item = cartItemRepository.findByCartUserUsernameAndProductId(name, productId).orElseThrow(() -> new ResourceNotFoundException("Cannot find cart item"));
        item.setChecked(checked);
    }

    public void deleteCartItems(String name) {
        Cart cart = cartRepository.findByUserUsername(name).orElseThrow(() -> new ResourceNotFoundException("under this user dont have cart"));
        cartItemRepository.deleteByCart(cart);
    }

}
