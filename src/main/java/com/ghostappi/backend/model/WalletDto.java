package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WalletDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWallet;

    @Column(name = "idUser", nullable = false)
    private Integer userId; // Cambiamos a Integer para que se maneje directamente

    public int getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }

    @JsonProperty("idUser") // Para mostrar como "idUser" en JSON
    public Integer getUserId() {
        return userId; // Retorna directamente el userId
    }

    public void setUserId(Integer userId) {
        this.userId = userId; // Establece solo el userId
    }

    @Override
    public String toString() {
        return "Wallet [idWallet=" + idWallet + ", userId=" + userId + "]";
    }
}
