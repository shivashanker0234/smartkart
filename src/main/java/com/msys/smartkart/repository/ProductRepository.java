package com.msys.smartkart.repository;

import com.msys.smartkart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductByProductNameEqualsIgnoreCase(final String name);

    List<Product> findProductByProductNameContainsIgnoreCase(final String name);
}

