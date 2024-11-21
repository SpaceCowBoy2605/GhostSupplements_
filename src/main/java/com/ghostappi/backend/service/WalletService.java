package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.dto.WalletDto;
import com.ghostappi.backend.model.Wallet;
import com.ghostappi.backend.repository.WalletRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class WalletService {

    @Autowired
    private WalletRepository wallpor;

   public Page<Wallet> getAll(Pageable pageable) {
        return wallpor.findAll(pageable);
    }
    
    public String save(WalletDto walletDto) {

        Wallet wallet = new Wallet();
        wallet.setUserId(walletDto.getUserId());
        wallpor.save(wallet); 
        return "Wallet created successfully"; 
    }

    public Wallet getIdWallet(Integer idWallet) {
        return wallpor.findById(idWallet).orElse(null);
    }

    public List<Wallet> getWalletsByUserId(Integer userId) {
        return wallpor.findByUserId(userId);
    }
    
    public void delete(Integer idWallet) {
        wallpor.deleteById(idWallet);
    }
}


