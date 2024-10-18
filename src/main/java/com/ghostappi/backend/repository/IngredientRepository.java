package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghostappi.backend.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
