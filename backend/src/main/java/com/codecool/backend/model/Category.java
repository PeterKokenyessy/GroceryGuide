package com.codecool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@ConditionalOnProperty(name = "spring.datasource.url")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 500)
    private String name;

    public Category(String name) {
        this.name = name;
    }

}


