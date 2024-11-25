package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.dto.WalletDto;
import com.ghostappi.backend.model.Wallet;
import com.ghostappi.backend.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallets")
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name="Wallets", description="Provides methods for managing wallets")
public class WalletController {
    @Autowired
    private WalletService wallser;
    
    @Operation(summary = "Get wallets with pagination" ,description = "Return a list of all wallets with pagination")
    @GetMapping
    public ResponseEntity<List<Wallet>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Wallet> wallets = wallser.getAll(pageable);

        return new ResponseEntity<>(wallets.getContent(), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Get wallet by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found wallet correctly", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Wallet.class))
        }),
        @ApiResponse(responseCode = "404", description = "wallet not found, please verify your information"),
    })

    @GetMapping("{idWallet}")
    public ResponseEntity<Wallet> getIdWallet(@PathVariable Integer idWallet) {
    Wallet wallet = wallser.getIdWallet(idWallet);
    return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

     @Operation(summary = "Get wallet by user id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User Found correctly", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Wallet.class))
        }),
        @ApiResponse(responseCode = "404", description = "The user has no wallet")
    })
      @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wallet>> getWalletsByUserId(@PathVariable Integer userId) {
        List<Wallet> wallets = wallser.getWalletsByUserId(userId);
        if (!wallets.isEmpty()) {
            return new ResponseEntity<>(wallets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        //post
        @Operation(summary = "Registers a new wallet")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The request has been successful and the card has been successfully add.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Wallet.class))
        }),
        @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
            @Content
        })
    })
       
   @PostMapping
    public ResponseEntity<String> createWallet(@Valid @RequestBody WalletDto walletDto) {
            String result = wallser.save(walletDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
