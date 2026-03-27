package com.codecool.backend.service;

import com.codecool.backend.dto.CartDto;
import com.codecool.backend.dto.CartItemDto;
import com.codecool.backend.dto.CartStoresDto;
import com.codecool.backend.mapper.CartItemMapper;
import com.codecool.backend.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartOptimizerServiceTest {

    private CartOptimizerService optimizer;
    private CartItemMapper cartItemMapper;

    @BeforeEach
    void setup() {
        cartItemMapper = mock(CartItemMapper.class);

        optimizer = new CartOptimizerService(cartItemMapper);
    }

    @Test
    void shouldChooseSingleStoreWhenAllProductsAvailable() {

        Store lidl = new Store("LIDL");

        Product milk = product("Milk",
                price(300, lidl));

        Product bread = product("Bread",
                price(200, lidl));

        Cart cart = cart(
                item(milk,1),
                item(bread,1)
        );

        when(cartItemMapper.toDto(any()))
                .thenReturn(new CartItemDto(
                        1L,"Milk",1.0,"l",1,"Dairy","Brand",List.of(),false,""
                ));

        CartDto result = optimizer.optimizeCart(cart);

        assertEquals(500, result.totalPrice());

        assertEquals(1, result.cart().size());

        CartStoresDto store = result.cart().get(0);

        assertEquals("LIDL", store.storeName());
        assertEquals(500, store.storeTotal());

        assertEquals(2, store.items().size());
    }

    @Test
    void shouldUseMultipleStores() {

        Store lidl = new Store("LIDL");
        Store spar = new Store("SPAR");

        Product milk = product("Milk",
                price(300, lidl));

        Product cheese = product("Cheese",
                price(500, spar));

        Cart cart = cart(
                item(milk,1),
                item(cheese,1)
        );

        when(cartItemMapper.toDto(any()))
                .thenReturn(new CartItemDto(
                        1L,"Test",1.0,"db",1,"Test","Brand",List.of(),false,""
                ));

        CartDto result = optimizer.optimizeCart(cart);

        assertEquals(800, result.totalPrice());
        assertEquals(2, result.cart().size());
    }

    @Test
    void shouldMultiplyQuantityCorrectly() {

        Store lidl = new Store("LIDL");

        Product milk = product("Milk",
                price(300, lidl));

        Cart cart = cart(
                item(milk,3)
        );

        when(cartItemMapper.toDto(any()))
                .thenReturn(new CartItemDto(
                        1L,"Milk",1.0,"l",1,"Dairy","Brand",List.of(),false,""
                ));

        CartDto result = optimizer.optimizeCart(cart);

        assertEquals(900, result.totalPrice());
    }

    @Test
    void emptyCartShouldReturnZero() {

        Cart cart = new Cart();
        cart.setItems(List.of());

        CartDto result = optimizer.optimizeCart(cart);

        assertEquals(0, result.totalPrice());
    }


    private Product product(String name, Price... prices) {

        Product p = new Product();
        p.setName(name);

        List<Price> list = List.of(prices);

        for (Price price : list) {
            price.setProduct(p);
        }

        p.setPrices(list);

        return p;
    }

    private Price price(double value, Store store) {

        Price p = new Price();
        p.setPrice(value);
        p.setStore(store);
        return p;
    }

    private CartItem item(Product product, int quantity) {

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        return item;
    }

    private Cart cart(CartItem... items) {

        Cart cart = new Cart();
        cart.setItems(List.of(items));
        return cart;
    }
}