package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghostappi.backend.model.Wallet;
import com.ghostappi.backend.model.WalletDto;

@Repository
public interface WalletDTORepository extends JpaRepository<WalletDto, Integer> {

    void save(Wallet wallet);

}
