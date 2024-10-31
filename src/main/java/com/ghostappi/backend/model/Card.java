package com.ghostappi.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; // Asegúrate de que sea java.util.Date


@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCard;
    
    private int number;

    private String type;

    private Date expirationDate; // Debe ser java.util.Date

    private int cvv;
    private boolean isExpired;

    @ManyToOne
    @JoinColumn(name = "idWallet", nullable = false)
    @JsonBackReference // Ignora el objeto completo de Wallet en la respuesta JSON
    @JsonIgnore 
    private Wallet wallet; // Asegúrate de que esta relación esté bien configurada

    public int getIdCard() {
        return idCard;
    }
    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public int getCvv() {
        return cvv;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    public boolean isExpired() {
        return isExpired;
    }
    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }
    public Wallet getWallet() {
        return wallet;
    }
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    
}