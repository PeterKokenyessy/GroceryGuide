package com.codecool.backend.specification;

import com.codecool.backend.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    public static Specification<Product> hasCategory(String categoryName) {
        return (root, query, cb) ->
                cb.equal(cb.lower(root.get("category").get("name")), categoryName.toLowerCase());
    }

    public static Specification<Product> hasBrands(List<String> brands) {
        return (root, query, cb) -> root.get("brand").get("name").in(brands);
    }

    public static Specification<Product> hasName (String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+ name.toLowerCase() + "%" ));
    }
}
