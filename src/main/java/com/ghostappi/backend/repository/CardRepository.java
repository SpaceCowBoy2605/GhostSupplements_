package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghostappi.backend.model.Card;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
     // Debería devolver una lista de `Card`
}
