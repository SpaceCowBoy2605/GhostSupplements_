package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghostappi.backend.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
