package com.codecool.backend.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@ConditionalOnProperty(name = "spring.datasource.url")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double quantity;

    @Column(name = "unit")
    private String quantityUnit;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Price> prices;

    private String image_url;

    public Product(String name, Double quantity, String quantityUnit, Category category, Brand brand) {
        this.name = name;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.category = category;
        this.brand = brand;
    }
}



