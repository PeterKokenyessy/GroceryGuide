package com.codecool.backend.controller;

import com.codecool.backend.dto.CartDto;
import com.codecool.backend.dto.requestDto.CheckDto;
import com.codecool.backend.dto.requestDto.QuantityDto;
import com.codecool.backend.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Operations related to user shopping cart")
public class CartController {
    private final Optional<CartService> cartService;

    @GetMapping
    @Operation(summary = "Get optimized cart")
    public ResponseEntity<CartDto> getCart(Authentication authentication) {
            CartDto cartDto = getService().getUserCart(authentication.getName());

            return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/items/{productId}")
    public ResponseEntity<Void> addItem(
            Authentication authentication,
            @PathVariable Long productId,
            @RequestBody QuantityDto quantityDto
    ) {
        String username = authentication.getName();

        getService().addToCart(
                username,
                productId,
                quantityDto.quantity()
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/items/{productId}")
    @Operation(summary = "Delete cartItem by productId")
    public ResponseEntity<Void> removeItem (
            Authentication authentication,
            @PathVariable Long productId
    ) {
        getService().deleteCartItem(authentication.getName(), productId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/items/{productId}")
    @Operation(summary = "Update quantity by productId")
    public ResponseEntity<Void> updateQuantity (
            Authentication authentication,
            @PathVariable Long productId,
            @RequestBody QuantityDto quantityDto
    ) {

        getService().updateQuantity(authentication.getName(), productId, quantityDto.quantity());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/items/checked/{productId}")
    @Operation(summary = "Update checked by productId")
    public ResponseEntity<Void> updateChecked (
            Authentication authentication,
            @PathVariable Long productId,
            @RequestBody CheckDto checkDto
    ) {
        getService().updateChecked(authentication.getName(),productId,checkDto.checked());

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete")
    @Operation(summary = "Delete all cart item in the cart")
    public ResponseEntity<Void> deleteAllItem (
            Authentication authentication
    ) {
        getService().deleteCartItems(authentication.getName());

        return ResponseEntity.ok().build();
    }

    private CartService getService() {
        return cartService.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Database not available"));
    }
}
