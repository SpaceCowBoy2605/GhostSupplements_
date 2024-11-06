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

import com.ghostappi.backend.service.ExcerciseService;
import com.ghostappi.backend.model.Excercise;

@RestController
@RequestMapping("/excercises")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT
})
@Tag(name = "Excercises", description = "Methods required to manage excercises")
public class ExcerciseController {
    
        @Autowired
        private ExcerciseService excerciseService;
    
        @Operation(summary = "Get all excercises", description = "Get all excercises")
        @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Excercise.class)))
        })    
        @GetMapping
        public List<Excercise> getAll() {
            return excerciseService.getAll();
        }

        @Operation(summary = "Get excercise by id", description = "Get excercise by id")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Excercise.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
        })
        @GetMapping("/{idExcercise}")
        public Excercise getById(@RequestParam Integer idExcercise) {
            return excerciseService.getById(idExcercise);
        }

        @Operation(summary = "Create a new excercise", description="Create a new excercise, includes the name ant difficulty")
        @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Excercise created" ,content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Excercise.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
        }

        )
        @PostMapping
        public ResponseEntity<?> save(@RequestBody Excercise excercise) {
            excerciseService.save(excercise);
            return new ResponseEntity<>("Excercise saved", HttpStatus.CREATED);
        }
        
        @Operation(summary = "Update an existing excercise", description="Update an existing excercise")
        @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Excercise created" ,content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Excercise.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
        })

        @PutMapping("{idExcercise}")
        public ResponseEntity<?> update(@RequestBody Excercise excercise, @PathVariable Integer idExcercise){
            if(!Objects.equals(excercise.getIdExcercise(), idExcercise)){
                throw new IllegalArgumentException("The provider identifier do not match");
            }

            Excercise excersiteToUpdate = excerciseService.getById(idExcercise);
            excersiteToUpdate.setName(excercise.getName());
            excersiteToUpdate.setDifficulty(excercise.getDifficulty());

            excerciseService.save(excersiteToUpdate);
            return new ResponseEntity<>(excersiteToUpdate, HttpStatus.OK);
        }

}
