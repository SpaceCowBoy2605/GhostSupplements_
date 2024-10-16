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
import com.ghostappi.backend.model.Wallet;
import com.ghostappi.backend.service.WalletService;

@RestController
@RequestMapping("/Wallet")
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class WalletController {
    @Autowired
    private WalletService wallser;
    
    @GetMapping
    public List<Wallet>getAll(){
        return wallser.getAll();
    }

    //GET
    @Operation(summary = "Get wallet by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found wallet correctly", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Wallet.class))
        }),
        @ApiResponse(responseCode = "404", description = "wallet not found, please verify your information"),
        @ApiResponse(responseCode = "400", description = "incorrectly entered data, verify the data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "Internal server error, please stand by", content = {
            @Content
        })
    })

    @GetMapping("{idWallet}")
    public ResponseEntity<Wallet> getIdWallet(@PathVariable Integer idWallet) {
    Wallet wallet = wallser.getIdWallet(idWallet);
    return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    //post
    @Operation(summary = "registers a new wallet")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "The request has been successful and the card has been successfully add.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Wallet.class))
    }),
    @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
        @Content
    }),
    @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
        @Content
    })
})
    @PostMapping
    public void register(@RequestBody Wallet wallet) {
    wallser.save(wallet);
  }

    //update
    @Operation(summary = "Update an existing wallet")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Wallet.class))
        }),
        @ApiResponse(responseCode = "404", description = "Wallet with ID not found"),
        @ApiResponse(responseCode = "400", description = "Invalid wallet data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred", content = {
            @Content
        })
    })
    @PutMapping("{idWallet}")
    public ResponseEntity<?> update(@RequestBody Wallet wallet, @PathVariable Integer idWallet) {
        Wallet existingWallet = wallser.getIdWallet(idWallet);
        wallet.setIdWallet(existingWallet.getIdWallet());  // Ensure id is not overwritten
        wallser.save(wallet);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    //delete
    @Operation(summary = "Delete a wallet by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "The wallet has been successfully deleted."),
        @ApiResponse(responseCode = "404", description = "Wallet with ID not found."),
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
    @DeleteMapping("{idWallet}")
    public void delete(@PathVariable Integer idWallet) {
        wallser.delete(idWallet);
    }
}
