package com.ghostappi.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import com.ghostappi.backend.service.NutrientProductService;
import com.ghostappi.backend.model.NutrientProduct;

@RestController
@RequestMapping("nutrientProructs")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "NutrientProduct", description = "APIs for managing nutrient products")
public class NutrientProductController {

    @Autowired
    private NutrientProductService service;

    @Operation(summary = "List all nutrient products")
    @ApiResponse(responseCode = "200", description = "Found all nutrient products" , 
        content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NutrientProduct.class)))})
    @GetMapping
    public List<NutrientProduct> getAll(){
        return service.getAll();
    }
}