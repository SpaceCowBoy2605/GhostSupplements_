package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghostappi.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
