package com.ghostappi.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.dto.CardDTO;
import com.ghostappi.backend.dto.CardDTO2;
import com.ghostappi.backend.model.Card;
import com.ghostappi.backend.model.Wallet;
import com.ghostappi.backend.repository.CardRepository;
import com.ghostappi.backend.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private WalletRepository walletRepository;

    // public List<CardDTO> getAll() {
    //     List<Card> cards = cardRepository.findAll();
    //     return cards.stream()
    //                 .map(this::convertToDTO)
    //                 .collect(Collectors.toList());
    // }

    public Page<CardDTO> getAll(Pageable pageable) {
        Page<Card> cardsPage = cardRepository.findAll(pageable);
        return cardsPage.map(this::convertToDTO); // Convertimos cada entidad Card a DTO
    }

    public String updateCard(CardDTO cardDTO) {
        Card existingCard = cardRepository.findById(cardDTO.getIdCard()).orElse(null);
        if (existingCard == null) {
            return "Card not found";
        }

        existingCard.setNumber(cardDTO.getNumber());
        existingCard.setType(cardDTO.getType());
        existingCard.setExpirationDate(cardDTO.getExpirationDate());
        existingCard.setCvv(cardDTO.getCvv());
        existingCard.setExpired(cardDTO.isExpired());

        if (cardDTO.getWalletId() != null) {
            Wallet wallet = walletRepository.findById(cardDTO.getWalletId()).orElse(null);
            if (wallet != null) {
                existingCard.setWallet(wallet);
            }
        }
        cardRepository.save(existingCard);
        return "Card updated successfully";
    }


    public String save(CardDTO cardDTO) {
        
        if (cardDTO.getWalletId() == null || !walletRepository.existsById(cardDTO.getWalletId())) {
            return "Wallet ID is invalid or does not exist";
        }

        Card card = new Card();
        card.setNumber(cardDTO.getNumber());
        card.setType(cardDTO.getType());
        card.setExpirationDate(cardDTO.getExpirationDate());
        card.setCvv(cardDTO.getCvv());
        card.setExpired(cardDTO.isExpired());
        
        Wallet wallet = walletRepository.findById(cardDTO.getWalletId()).orElse(null);
        if (wallet != null) {
            card.setWallet(wallet);
        }

        cardRepository.save(card);
        
        return "Card saved successfully";
    }

    
    public CardDTO getIdCard(Integer idCard) {
        Card card = cardRepository.findById(idCard).orElse(null);
        return card != null ? convertToDTO(card) : null;
    }
    

        public CardDTO2 getCardsByUserId(Integer userId) {
        List<Card> cards = cardRepository.findByWalletUserId(userId);

        CardDTO2 cardDTO2 = new CardDTO2();
        cardDTO2.setUserId(userId);
        cardDTO2.setCards(cards.stream().map(this::convertToDTO).collect(Collectors.toList())); 

        return cardDTO2;
    }

    public void delete(Integer idCard) {
        cardRepository.deleteById(idCard);
    }

    private CardDTO convertToDTO(Card card) {
    CardDTO dto = new CardDTO();
    dto.setIdCard(card.getIdCard());
    dto.setNumber(card.getNumber());
    dto.setType(card.getType());
    dto.setExpirationDate(card.getExpirationDate());
    dto.setCvv(card.getCvv());
    dto.setExpired(card.isExpired());

        if (card.getWallet() != null) {
            dto.setWalletId(card.getWallet().getIdWallet());
        } else {
            dto.setWalletId(null); 
        }

        return dto;
    }
}

