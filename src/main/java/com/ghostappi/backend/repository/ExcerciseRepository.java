package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ghostappi.backend.model.Excercise;

public interface ExcerciseRepository extends JpaRepository<Excercise, Integer> {

}
