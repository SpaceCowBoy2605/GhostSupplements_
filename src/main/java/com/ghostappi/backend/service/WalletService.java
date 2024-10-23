package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Wallet;
import com.ghostappi.backend.repository.WalletRepository;
import jakarta.transaction.Transactional;


    @Service
    @Transactional
    public class WalletService {
    @Autowired
    private WalletRepository wallpor;

    public List<Wallet>getAll(){
       return wallpor.findAll();
    }

    public void save(Wallet wallet){
        wallpor.save(wallet);
    }

    public Wallet getIdWallet(Integer idWallet){
        return wallpor.findById(idWallet).get();
    }

    public void delete(Integer idWallet){
    wallpor.deleteById(idWallet);
  }
}

