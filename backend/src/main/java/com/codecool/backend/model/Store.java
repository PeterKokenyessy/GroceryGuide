package com.codecool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@ConditionalOnProperty(name = "spring.datasource.url")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    public Store(String name) {
        this.name = name;
    }
}


