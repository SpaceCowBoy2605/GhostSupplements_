package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.dto.CardDTO;
import com.ghostappi.backend.dto.CardDTO2;
import com.ghostappi.backend.model.Card;
import com.ghostappi.backend.service.CardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Cards", description = "Provides methods for managing cards")
public class CardController {

    @Autowired
    private CardService service;

    @Operation(summary = "Get Cards with pagination", description = "Return a list of all Cards with pagination")
    @GetMapping
    public ResponseEntity<List<CardDTO>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CardDTO> cards = service.getAll(pageable);
        return new ResponseEntity<>(cards.getContent(), HttpStatus.OK); // Solo los datos
    }

    // get
    @Operation(summary = "Get Card by Id user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found user", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
        }),
        @ApiResponse(responseCode = "404", description = "User not found, please verify your information")
    })

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCardsByUserId(@PathVariable Integer userId) {
        CardDTO2 cardDTO2 = service.getCardsByUserId(userId);
        if (cardDTO2 != null && !cardDTO2.getCards().isEmpty()) {
            return new ResponseEntity<>(cardDTO2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No cards found for the requested user", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get Card by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found Card", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
        }),
        @ApiResponse(responseCode = "404", description = "Card not found, please verify your information")
    })
    @GetMapping("{idCard}")
    public ResponseEntity<?> getIdCard(@PathVariable Integer idCard) {
        CardDTO cardDTO = service.getIdCard(idCard);
        if (cardDTO != null) {
            return new ResponseEntity<>(cardDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }
    }

    // post
    @Operation(summary = "Register a new card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The request has been successful and the Card has been successfully created.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
        }),
        @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
            @Content
        })
    })
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody CardDTO cardDTO) {
        String result = service.save(cardDTO);
        if (result.equals("Card saved successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    // update
    @Operation(summary = "Update an existing card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
        }),
        @ApiResponse(responseCode = "404", description = "Card with ID not found")
    })
    @PutMapping("/{idCard}")
    public ResponseEntity<?> updateCard(@PathVariable int idCard, @Valid @RequestBody CardDTO cardDTO) {
        if (cardDTO.getIdCard() != idCard) {
            return new ResponseEntity<>("ID in the path does not match ID in the request body", HttpStatus.BAD_REQUEST);
        }
        String response = service.updateCard(cardDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // delete
    @Operation(summary = "Delete a card by ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "The card has been successfully deleted."),
    @ApiResponse(responseCode = "404", description = "Card with ID not found."),})
    @DeleteMapping("{idCard}")
    public ResponseEntity<?> delete(@PathVariable Integer idCard) {
        CardDTO cardDTO = service.getIdCard(idCard);
        if (cardDTO == null) {
            return new ResponseEntity<>("Card with ID not found.", HttpStatus.NOT_FOUND);
        }
        service.delete(idCard);
        return new ResponseEntity<>("Card deleted successfully", HttpStatus.OK);
    }
}
