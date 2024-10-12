package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.service.NutrientService;
import com.ghostappi.backend.model.Nutrient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/nutrients")
@CrossOrigin(origins = "*", 
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT
})

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "GhostSupplements API",
        description = "API for nutrient management",
        version = "1.0"
    )
)

@Tag(name = "Nutrient", description = "APIs related to Nutrient")

public class NutrientController {
    
    @Autowired
    private NutrientService nutrientService;

    @Operation(
        summary = "Get all nutrients", 
        description = "Get all nutrients from the database"
        )
    @ApiResponse(
        responseCode = "200", 
        description = "Success", 
        content = {
            @Content(
                mediaType = "application/json", 
                array = @ArraySchema(
                    schema = @Schema(
                        implementation = Nutrient.class)))
    })
    
    @GetMapping
    public List<Nutrient> getAll() {
        return nutrientService.getAll();
    }

}
