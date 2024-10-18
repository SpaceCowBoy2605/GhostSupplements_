package com.ghostappi.backend.controller;

import java.util.List;
import java.util.Objects;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
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
import jakarta.persistence.TableGenerator;

import com.ghostappi.backend.service.IngredientService;
import com.ghostappi.backend.model.Ingredient;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT
})
@Tag(name = "Ingredient", description = "APIs related to Ingredient")

public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    
    @Operation(summary = "Get all ingredients", description = "Get all ingredients from the database")
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
    })
    @GetMapping
    public List<Ingredient>getAll(){
        return ingredientService.getAll();
    }

    @Operation(summary = "Get ingredient by id", description = "Get ingredient by id from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/{idIngredient}")
    public Ingredient getById(@PathVariable Integer id){
        return ingredientService.getById(id);
    }

    @Operation(summary = "Save ingredient", description = "Save ingredient in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Ingredient ingredient){
        ingredientService.save(ingredient);
        return ResponseEntity.ok("Ingredient saved");
    }

    @PutMapping("{idNIngredient}")
    public ResponseEntity<Ingredient>update(@RequestBody Ingredient ingredient, @PathVariable Integer idIngredient){
        if(!Objects.equals(ingredient.getIdIngredient(), idIngredient))
            throw new IllegalIdentifierException(" The provider idetinfier do not match ");
            Ingredient ingredientToUpdate = ingredientService.getById(idIngredient);
            ingredientToUpdate.setName(ingredient.getName());
            ingredientService.save(ingredientToUpdate);
        return new ResponseEntity<>(ingredientToUpdate, HttpStatus.OK);
    }   

}