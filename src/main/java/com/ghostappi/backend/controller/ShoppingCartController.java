package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.service.ShoppingCartService;
import com.ghostappi.backend.model.ShoppingCart;

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

@RestController
@RequestMapping("shoppingcart")
@Tag(name = "Controller of shopping carts")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Operation(summary = "Register a shopping cart")
	@PostMapping
	public ResponseEntity<String> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
		shoppingCartService.saveShoppingCart(shoppingCart);
		return new ResponseEntity<>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Get a shopping cart by his id of the shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Coupon found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id of coupon supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Coupon not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<?> getShoppingCartById(@PathVariable Integer id) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByCustomId(id);
		if (shoppingCart != null) {
			return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Shopping Cart not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/customId/{idShoppingCart}")
	public ResponseEntity<?> getShoppingCartByCustomId(@PathVariable Integer idShoppingCart) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByCustomId(idShoppingCart);
		if (shoppingCart != null) {
			return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Shopping Cart not found", HttpStatus.NOT_FOUND);
		}
	}

	@ApiResponse(responseCode = "200", description = "Found Shooping Carts", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShoppingCart.class))) })
	@GetMapping
	public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
		List<ShoppingCart> carts = shoppingCartService.getAllShoppingCarts();
		return new ResponseEntity<>(carts, HttpStatus.OK);
	}

	@Operation(summary = "Update a shooping cart with his id")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateShoppingCartById(@PathVariable Integer id, @RequestBody ShoppingCart updatedCart) {
		ShoppingCart shoppingCart = shoppingCartService.updateShoppingCart(id, updatedCart);
		if (shoppingCart != null) {
			return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Shopping Cart not found", HttpStatus.NOT_FOUND);

		}
	}

	@Operation(summary = "Delete a shopping cart with his id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteShoppingCartById(@PathVariable Integer id) {
		try {
			shoppingCartService.deleteShoppingCartByCustomId(id);
			return new ResponseEntity<>("Delete record", HttpStatus.OK);
		} catch (EntityNotFoundException e) {

			return new ResponseEntity<>("Shopping Cart not found", HttpStatus.NOT_FOUND);
		}
	}

}
