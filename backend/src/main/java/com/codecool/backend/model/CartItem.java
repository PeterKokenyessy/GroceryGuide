package com.codecool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@ConditionalOnProperty(name = "spring.datasource.url")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private boolean checked;

    public CartItem (Cart cart,Product product,Integer quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

}
