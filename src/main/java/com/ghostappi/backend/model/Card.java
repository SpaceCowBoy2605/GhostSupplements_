package com.ghostappi.backend.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCard;
    
    private int number;

    @NotBlank(message = "The description must no be null and contain at least one character")
    private String type;

    @NotNull(message = "The expiration date must not be null")
    private Date expirationDate;

    private int cvv;

    private boolean isExpired;
    /* 
    @ManyToOne
    @JoinColumn(name = "idWallet")
    @JsonIgnore // Ignora el objeto completo de Wallet en la respuesta JSON
    private Wallet wallet; 
    */

    // Getters y Setters estándar
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

    /* 
    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
    */
    // Añadir el método para obtener solo el idWallet
    //@JsonProperty("idWallet") // Para que se muestre en la respuesta JSON como "idWallet"
    //public Integer getWalletId() {
    //    return wallet != null ? wallet.getIdWallet() : null; // Solo devuelve el idWallet
    //}
}

