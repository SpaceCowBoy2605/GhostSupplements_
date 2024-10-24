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
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.model.Card;
import com.ghostappi.backend.service.CardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/Card")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name="Card")
public class CardController {
    @Autowired
  private CardService service;  

  @GetMapping
  public List<Card> getAll() {
    return service.getAll();
  }

    // get
    @Operation(summary = "Get Card by ID")
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
             Card card = service.getIdCard(idCard);
            return new ResponseEntity<>(card, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }
           
    }

    // post
    @Operation(summary = "Register a new card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "The request has been successful and the Card has been successfully created.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
        }),
        @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
            @Content
        })
    })
    @PostMapping
    public ResponseEntity<?> register(@RequestBody Card car) {
        try {
            service.save(car);
            return new ResponseEntity<>("Card add correctly", HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>("Card not add, verify data", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> update(@RequestBody Card Card, @PathVariable Integer idCard) {
        try {
            Card existingCard = service.getIdCard(idCard);
            Card.setIdCard(existingCard.getIdCard()); 
            service.save(Card);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        } catch (Exception e) {
             return new ResponseEntity<String>("Updated no record, verify your information", HttpStatus.BAD_REQUEST);
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
            // Verificar si la tarjeta existe
            Card card = service.getIdCard(idCard);
            if (card == null) {
                // Si no existe, retornar 404
                return new ResponseEntity<>("Card with ID not found.", HttpStatus.NOT_FOUND);
            }
            // Si existe, proceder a eliminar
            service.delete(idCard);
            return new ResponseEntity<>("Card deleted successfully", HttpStatus.OK); 
        } catch (Exception e) {
            // Manejar otros errores inesperados
            return new ResponseEntity<>("Error deleting card: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
