package com.ghostappi.backend.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCard;
    private int number;

    @NotBlank(message = "The description must no be null and containn at least one character")
    private String type;
    
    @NotBlank(message = "The description must no be null and containn at least one character")
    private Date expirationDate;

    //@Min(1)
    //@Max(999)
    //@Column(name = "cvv")
    //@JsonProperty("cvv")
    private int cvv;

    @NotBlank(message = "The description must no be null and containn at least one character")
    //@Column(name = "description")
    //@JsonProperty("description")
    private boolean isExpired;
/* 
    @ManyToOne
    @JoinColumn(name = "idWallet")
    private Wallet wallet; 
*/

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

	//@Override
	//public String toString() {
	//	return "Card [idCard=" + idCard + ", number=" + number + ", type=" + type + ", expirationDate=" + expirationDate
	//			+ ", cvv=" + cvv + ", isExpired=" + isExpired + "]";
	//}
    
}


