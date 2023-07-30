package com.example.lap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Simple queries in Repositories can be simply defined by the name of the method.
 * If more complicated methods are needed the @Query annotation can be used on the method.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Long id);

    Set<Product> findProductByIdIn(Set<Long> ids);

    /**
     * @param amount how many products should be returned
     * @return list of the rarest products
     */
    @Query(value = "select p.*, count(id) as count from product as p inner join ordering_product as op on p.id = op.product_id group by op.product_id order by count asc limit :amount", nativeQuery = true)
    List<Product> findRarestProducts(@Param("amount") int amount);

    /**
     * @param amount how many products should be returned
     * @return list of the most popular products
     */
    @Query(value = "select p.*, count(id) as count from product as p inner join ordering_product as op on p.id = op.product_id group by op.product_id order by count desc limit :amount", nativeQuery = true)
    List<Product> findMostPopularProducts(@Param("amount") int amount);

    /**
     * @param days how many days the timespan is
     * @return list of products that were ordered in the given timespan before today
     */
    @Query(value = "select p.* from product as p inner join ordering_product as op on p.id = op.product_id inner join ordering as o on o.id = op.ordering_id where DATEDIFF(ordering_date, NOW()) <= :days group by p.id", nativeQuery = true)
    List<Product> findAllProductsOfTheLastTimespan(@Param("days") int days);
}

