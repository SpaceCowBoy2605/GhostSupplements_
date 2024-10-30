package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Wallet;
import com.ghostappi.backend.model.WalletDto;
import com.ghostappi.backend.repository.WalletRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class WalletService {

    @Autowired
    private WalletRepository wallpor;

    public List<Wallet> getAll() {
        return wallpor.findAll();
    }

    public String save(WalletDto walletDto) {
        // Crear un nuevo objeto Wallet a partir del WalletDto
        Wallet wallet = new Wallet();
        wallet.setUserId(walletDto.getUserId());
        // Establece otros campos si es necesario

        wallpor.save(wallet); // Guarda el objeto Wallet en la base de datos
        return "Wallet created successfully"; // Mensaje de éxito
    }

    public Wallet getIdWallet(Integer idWallet) {
        // Manejar el caso donde no se encuentra la Wallet
        return wallpor.findById(idWallet).orElse(null);
    }

    public void delete(Integer idWallet) {
        wallpor.deleteById(idWallet);
    }
}


