package com.ghostappi.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ghostappi.backend.service.CouponService;
import com.ghostappi.backend.model.Coupon;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("coupons")
@CrossOrigin(origins = "*")
@Tag(name = "Coupons", description="Provides methods for managing coupons")
public class CouponController {
	@Autowired
	private CouponService service;

	/*@Operation(summary = "Get all coupons")
	@ApiResponse(responseCode = "200", description = "Found Coupons", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Coupon.class))) })
	@GetMapping
	public List<Coupon> getAll() {
		return service.getAll();
	}*/
	@Operation(summary = "Get all active coupons")
    @ApiResponse(responseCode = "200", description = "Found active coupons", content = {
		@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Coupon.class))) })
    @GetMapping("/active")
    public List<Coupon> getActiveCoupons() {
        return service.getActiveCoupons();
    }
	@Operation(summary = "Get all inactive coupons")
    @ApiResponse(responseCode = "200", description = "Found inactive coupons", content = {
		@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Coupon.class))) })
    @GetMapping("/inactive")
    public List<Coupon> getInactiveCoupons() {
        return service.getInactiveCoupons();
    }

	@Operation(summary = "Get a coupon by his id of the coupon")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Coupon found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Coupon.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id of coupon supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Coupon not found", content = @Content) })
	@GetMapping("{idCoupon}")
	public ResponseEntity<?> getByidCoupon(@PathVariable Integer idCoupon) {
		Coupon coupon = service.getByidCoupon(idCoupon);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}

	@Operation(summary = "Register a coupon ")
	@PostMapping
	public ResponseEntity<?> register(@RequestBody Coupon coupon) {
		service.save(coupon);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Update a coupon with his id")
	@PutMapping("{idCoupon}")
	public ResponseEntity<?> update(@RequestBody Coupon coupon, @PathVariable Integer idCoupon) {
		Coupon auxCoupon = service.getByidCoupon(idCoupon);
		coupon.setIdCoupon(auxCoupon.getIdCoupon());
		service.save(coupon);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

	/*@Operation(summary = "Delete a coupon with his id")
	@DeleteMapping("{idCoupon}")
	public ResponseEntity<?> delete(@PathVariable Integer idCoupon) {
		service.delete(idCoupon);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}*/

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
