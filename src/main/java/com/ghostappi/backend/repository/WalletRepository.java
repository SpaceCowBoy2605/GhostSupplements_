package com.ghostappi.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghostappi.backend.model.Wallet;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>  {
    List<Wallet> findByUserId(Integer userId);
}
