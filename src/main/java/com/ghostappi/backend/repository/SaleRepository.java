package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ghostappi.backend.model.Sale;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

} 