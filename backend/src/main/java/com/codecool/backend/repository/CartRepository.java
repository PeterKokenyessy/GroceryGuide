package com.codecool.backend.repository;

import com.codecool.backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("""
SELECT DISTINCT c FROM Cart c
LEFT JOIN FETCH c.items ci
LEFT JOIN FETCH ci.product
WHERE c.user.username = :username
""")
    Optional<Cart> findByUserUsername(@Param("username") String username);
}
