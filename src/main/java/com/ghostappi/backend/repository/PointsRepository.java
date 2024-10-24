package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghostappi.backend.model.Points;

@Repository
public interface PointsRepository extends JpaRepository<Points, Integer> {

}
