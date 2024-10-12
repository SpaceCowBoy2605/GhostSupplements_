package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ghostappi.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
