package com.example.lap.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Simple queries in Repositories can be simply defined by the name of the method.
 * If more complicated methods are needed the @Query annotation can be used on the method.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Long id);

    Set<Product> findProductByIdIn(Set<Long> ids);
}

