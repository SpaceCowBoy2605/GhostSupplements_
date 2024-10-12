package com.ghostappi.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCard;
    private int number;
    private int type;
    private int expirationDate;
    private int cvv;
    private boolean isExpired;
    @ManyToOne
    @JoinColumn(name = "idWallet")
    private Wallet wallet; 

    public Card(int idCard, int number, int type, int expirationDate, int cvv, boolean isExpired, Wallet wallet) {
        this.idCard = idCard;
        this.number = number;
        this.type = type;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.isExpired = isExpired;
        this.wallet = wallet;
    }

    public Card() {
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
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

    @Override
    public String toString() {
        return "Card [idCard=" + idCard + ", number=" + number + ", type=" + type + ", expirationDate=" + expirationDate
                + ", cvv=" + cvv + ", isExpired=" + isExpired + ", wallet=" + wallet + "]";
    }

}

