package com.ghostappi.backend.controller;

import java.util.List;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

import com.ghostappi.backend.service.AdministrationViaService;
import com.ghostappi.backend.model.AdministrationVia;

@RestController
@RequestMapping("/administrationvias")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT
})
@Tag(name = "AdministrationVias", description = "Methods required to manage administration vias")
public class AdministrationViaController {

    @Autowired
    private AdministrationViaService service;

    @Operation(summary = "Get all administration vias", description = "Get all administration vias from the database")
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AdministrationVia.class)))
    })
    @GetMapping
    public List<AdministrationVia> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get administration via by id", description = "Get administration via by id from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AdministrationVia.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Administration Via not found" , content = @Content)
    })
    @GetMapping("{idAdministrationVia}")
    public AdministrationVia getById(@RequestParam Integer id) {
        return service.getById(id);
    }
    

    @Operation(summary = "Create a new administration via", description = "Create a new administration via in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administration Via created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AdministrationVia.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AdministrationVia> save(@RequestBody AdministrationVia administrationVia) {
        return new ResponseEntity<>(service.save(administrationVia), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an administration via", description = "Update an administration via in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administration Via updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AdministrationVia.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    })
    @PutMapping("{idAdminisrationVia}")
    public ResponseEntity<AdministrationVia> update(@RequestBody AdministrationVia administrationVia, @PathVariable Integer id) {
        if(!Objects.equals(administrationVia.getIdAdministrationVia(),id)){
            throw new IllegalArgumentException("The provider indentifers do not match");

        }
        AdministrationVia exAdministrationVia = service.getById(id);     
        exAdministrationVia.setName(administrationVia.getName());
        service.save(exAdministrationVia);
        return new ResponseEntity<>(exAdministrationVia, HttpStatus.OK);  
    }

}
