package com.ghostappi.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class WalletDTO2 {

    private Integer idUser; 
    private int idWallet;
    private List<CardDTO> cards = new ArrayList<>();

    public Integer getIdUser() {
        return idUser;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public int getIdWallet() {
        return idWallet;
    }
    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }
    public List<CardDTO> getCards() {
        return cards;
    }
    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

}
