package com.ghostappi.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.dto.CardDTO;
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

    public List<CardDTO> getAll() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }

    public String save(CardDTO cardDTO) {
        // Verificar si el walletId es válido antes de guardar
        if (cardDTO.getWalletId() == null || !walletRepository.existsById(cardDTO.getWalletId())) {
            return "Wallet ID is invalid or does not exist";
        }

        Card card = new Card();
        card.setNumber(cardDTO.getNumber());
        card.setType(cardDTO.getType());
        card.setExpirationDate(cardDTO.getExpirationDate());
        card.setCvv(cardDTO.getCvv());
        card.setExpired(cardDTO.isExpired());
        
        // Asigna el wallet usando el ID
        Wallet wallet = walletRepository.findById(cardDTO.getWalletId()).orElse(null);
        if (wallet != null) {
            card.setWallet(wallet); // Asegúrate de que Card tenga un método setWallet
        }

        cardRepository.save(card);
        
        return "Card saved successfully";
    }

    public CardDTO getIdCard(Integer idCard) {
        Card card = cardRepository.findById(idCard).orElse(null);
        return card != null ? convertToDTO(card) : null;
    }

    public void delete(Integer idCard) {
        cardRepository.deleteById(idCard);
    }

    // Método para convertir Card a CardDTO
    private CardDTO convertToDTO(Card card) {
    CardDTO dto = new CardDTO();
    dto.setIdCard(card.getIdCard());
    dto.setNumber(card.getNumber());
    dto.setType(card.getType());
    dto.setExpirationDate(card.getExpirationDate());
    dto.setCvv(card.getCvv());
    dto.setExpired(card.isExpired());

    // Asegúrate de que estás obteniendo el walletId correctamente
    if (card.getWallet() != null) {
        dto.setWalletId(card.getWallet().getIdWallet());
    } else {
        dto.setWalletId(null); // O maneja esto como prefieras
    }

    return dto;
}
}

  /* 
    @Autowired
    private CardRepository cardRepository;

     // Repositorio de Wallet para verificar existencia de Wallet

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

     public String save(Card card) {
        cardRepository.save(card);
        return "Card saved successfully";
    }

    public Card getIdCard(Integer idCard) {
        return cardRepository.findById(idCard).orElse(null);
    }

    public void delete(Integer idCard) {
        cardRepository.deleteById(idCard);
    }
        */



                /*
            if (card.getWalletId() == null) {
                return new ResponseEntity<>("Wallet ID is required", HttpStatus.BAD_REQUEST);
            }

            // Buscar el Wallet por ID
            Wallet wallet = walser.getIdWallet(card.getWalletId());
            if (wallet == null) {
                return new ResponseEntity<>("Wallet not found", HttpStatus.BAD_REQUEST);
            }
             */
            // Asignar el Wallet a la tarjeta
            //card.setWallet(wallet);

