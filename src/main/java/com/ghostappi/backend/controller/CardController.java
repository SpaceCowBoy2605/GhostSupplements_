package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ghostappi.backend.model.Card;
import com.ghostappi.backend.model.CardDTO;
import com.ghostappi.backend.service.CardService;
import com.ghostappi.backend.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("cards")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name="Cards", description="Provides methods for managing cards")
public class CardController {
    @Autowired
  private CardService service;  

 @Operation(summary = "Get all Cards")
  @GetMapping
public List<CardDTO> getAll() {
    
    return service.getAll();  
}
    // get
    @Operation(summary = "Get Card by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found Card :D", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
        }),
        @ApiResponse(responseCode = "404", description = "Card not found, please verify your information"),
        @ApiResponse(responseCode = "400", description = "incorrectly entered data, verify the data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content
        })
    })

    @GetMapping("{idCard}")
    public ResponseEntity<?> getIdCard(@PathVariable Integer idCard) {
        try {
            CardDTO cardDTO = service.getIdCard(idCard);  
            if (cardDTO != null) {
                return new ResponseEntity<>(cardDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving card", HttpStatus.INTERNAL_SERVER_ERROR);
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
        }),
         @ApiResponse(responseCode = "404", description = "Wallet no found", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
            @Content
        })
    })
        @PostMapping
    public ResponseEntity<String> register(@RequestBody CardDTO cardDTO) {
        try {
            String result = service.save(cardDTO);  
            if (result.equals("Card saved successfully")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Card not added, verify data", HttpStatus.BAD_REQUEST);
        }
    }

    //update 
    @Operation(summary = "Update an existing card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
        }),
        @ApiResponse(responseCode = "404", description = "Card with ID not found"),
        @ApiResponse(responseCode = "400", description = "Invalid Card data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred", content = {
            @Content
        })
    })
        @PutMapping("{idCard}")
public ResponseEntity<?> update(@RequestBody CardDTO cardDTO, @PathVariable Integer idCard) {
    try {
        CardDTO existingCardDTO = service.getIdCard(idCard); // Cambia a CardDTO
        if (existingCardDTO == null) {
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }

        // Actualiza el ID para asegurarte de que el ID sea correcto
        cardDTO.setIdCard(existingCardDTO.getIdCard());
        service.save(cardDTO); // Asegúrate de que el servicio acepte CardDTO

        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<String>("Update failed, verify your information", HttpStatus.BAD_REQUEST);
    }
}
        //delete
    @Operation(summary = "Delete a card by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The card has been successfully deleted."),
        @ApiResponse(responseCode = "404", description = "Card with ID not found."),
        @ApiResponse(responseCode = "400", description = "Invalid Card ID.", content = { @Content }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = { @Content })
    })
    @DeleteMapping("{idCard}")
public ResponseEntity<?> delete(@PathVariable Integer idCard) {
    try {
        CardDTO cardDTO = service.getIdCard(idCard);  // Cambia a CardDTO
        if (cardDTO == null) {
            return new ResponseEntity<>("Card with ID not found.", HttpStatus.NOT_FOUND);
        }

        service.delete(idCard);
        return new ResponseEntity<String>("Card deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<String>("Error deleting card: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
