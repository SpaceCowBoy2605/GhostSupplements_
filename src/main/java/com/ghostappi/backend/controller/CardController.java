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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.ghostappi.backend.model.Card;
import com.ghostappi.backend.service.CardService;


@RestController
@RequestMapping("/Card")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
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
    @ApiResponse(responseCode = "200", description = "Found wallet :D", content = {
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
  public ResponseEntity<Card> getIdCard(@PathVariable Integer idCard) {
    Card card = service.getIdCard(idCard);
    return new ResponseEntity<Card>(card, HttpStatus.OK);
  }

  // post
@Operation(summary = "Register a new card")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "The request has been successful and the wallet has been successfully created.", content = {
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
  public void register(@RequestBody Card wallet) {
    service.save(wallet);
  }
//update 
@Operation(summary = "Update an existing card")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Updated record", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Card.class))
    }),
    @ApiResponse(responseCode = "404", description = "Wallet with ID not found"),
    @ApiResponse(responseCode = "400", description = "Invalid wallet data", content = {
        @Content
    }),
    @ApiResponse(responseCode = "500", description = "An internal server error has occurred", content = {
        @Content
    })
})
@PutMapping("{idCard}")
public ResponseEntity<?> update(@RequestBody Card wallet, @PathVariable Integer idCard) {
    Card existingWallet = service.getIdCard(idCard);
    wallet.setIdCard(existingWallet.getIdCard());  // Ensure id is not overwritten
    service.save(wallet);
    return new ResponseEntity<String>("Updated record", HttpStatus.OK);
}
//delete
@Operation(summary = "Delete a card by ID")
@ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "The card has been successfully deleted."),
    @ApiResponse(responseCode = "404", description = "Card with ID not found."),
    @ApiResponse(responseCode = "400", description = "Invalid wallet ID.", content = {
        @Content
    }),
    @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
        @Content
    }),
    @ApiResponse(responseCode = "401", description = "Unauthorized. Please log in and try again.", content = {
        @Content
    }),
    @ApiResponse(responseCode = "403", description = "Forbidden. You do not have permission to perform this action.", content = {
        @Content
    })
})
  @DeleteMapping("{idCard}")
  public void delete(@PathVariable Integer idCard) {
    service.delete(idCard);
  }
}
