package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ghostappi.backend.model.Nutrient;

public interface NutrientRepository extends JpaRepository<Nutrient, Integer> {

}