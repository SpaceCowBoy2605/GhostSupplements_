package com.ghostappi.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.model.ShoppingCart;
import com.ghostappi.backend.service.ShoppingCartService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("shoppingcart")
@Tag(name = "Shopping Carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiResponse(responseCode = "200", description = "Found Shopping Carts", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShoppingCart.class))) })
    @GetMapping()
    public Iterable<ShoppingCart> getAll() {
        return shoppingCartService.getAll();
    }

    @Operation(summary = "Get a shopping cart by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Shopping cart found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Shopping cart not found", content = @Content) })
    @GetMapping("{idShoppingCart}")
    public ShoppingCart getById(@PathVariable Integer idShoppingCart) {
        return shoppingCartService.getById(idShoppingCart);
    }

    @Operation(summary = "Register a new shopping cart")
    @PostMapping()
    public ResponseEntity<String> add(@RequestBody ShoppingCart resource) {
        shoppingCartService.add(resource);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }

    @Operation(summary = "Update a shopping cart by its id")
    @PutMapping("{idShoppingCart}")
    public ResponseEntity<String> update(@RequestBody ShoppingCart resource, @PathVariable Integer idShoppingCart) {
        shoppingCartService.update(resource, idShoppingCart);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete a shopping cart by its id")
    @DeleteMapping("{idShoppingCart}")
    public ResponseEntity<String> delete(@PathVariable Integer idShoppingCart) {
        shoppingCartService.delete(idShoppingCart);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
