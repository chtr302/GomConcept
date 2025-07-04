package com.gomconcept.gomconcept.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gomconcept.gomconcept.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findBySlug(String slug);
}
