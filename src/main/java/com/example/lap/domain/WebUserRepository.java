package com.example.lap.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Simple queries in Repositories can be simply defined by the name of the method.
 * If more complicated methods are needed the @Query annotation can be used on the method.
 */
public interface WebUserRepository extends JpaRepository<WebUser, Integer> {
    WebUser findUserById(Long id);

    WebUser findUserByEmail(String email);
}

