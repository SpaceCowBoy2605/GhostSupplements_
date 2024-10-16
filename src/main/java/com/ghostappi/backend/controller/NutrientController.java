package com.ghostappi.backend.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ghostappi.backend.service.NutrientService;
import com.ghostappi.backend.model.Nutrient;

@RestController
@RequestMapping("/nutrients")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT
})

@Tag(name = "Nutrient", description = "APIs related to Nutrient")

public class NutrientController {

    @Autowired
    private NutrientService nutrientService;

    @Operation(summary = "Get all nutrients", description = "Get all nutrients from the database")
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Nutrient.class)))
    })

    @GetMapping
    public List<Nutrient> getAll() {
        return nutrientService.getAll();
    }


    @Operation(summary = "Get nutrient by id", description = "Get nutrient by id from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nutrient found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Nutrient.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nutrient not found", content = @Content)
    })

    @GetMapping("{idNutrient}")
    public Nutrient getById(@RequestParam int idNutrient) {
        return nutrientService.getById(idNutrient);
    }
    

    @Operation(summary = "Save nutrient", description = "Save nutrient in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nutrient added", content = {  
                @Content(mediaType = "application/json", schema = @Schema(implementation = Nutrient.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid nutrient", content = @Content)
    })
    @PostMapping
    public ResponseEntity<String>save(@RequestBody Nutrient nutrient){
        nutrientService.save(nutrient);
        return ResponseEntity.ok("Nutrient added");
    }

    
    @Operation(summary = "Update a nutrient", description = "Update a nutrient in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nutrient updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Nutrient.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid nutrient", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nutrient not found", content = @Content)
    })
    @PutMapping("{idNutrient}")
    public ResponseEntity<Nutrient> update(@RequestBody Nutrient nutrient ,@PathVariable Integer idNutrient) {
        if(!Objects.equals(nutrient.getIdNutrient(), idNutrient)){
            throw new IllegalArgumentException("The provider identifiers do not match");
        }
        Nutrient existingNutrient = nutrientService.getById(idNutrient);
        existingNutrient.setName(nutrient.getName());
        nutrientService.save(existingNutrient);
        return new ResponseEntity<>(existingNutrient, HttpStatus.OK);
    }
    
}
