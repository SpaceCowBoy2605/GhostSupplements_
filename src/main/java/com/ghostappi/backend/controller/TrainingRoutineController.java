package com.ghostappi.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ghostappi.backend.dto.TrainingRoutineDTO;
import com.ghostappi.backend.dto.TrainingRoutineRequestDTO;
import com.ghostappi.backend.service.TrainingRoutineService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/trainingroutines")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Training Routines", description = "Provides methods for managing training routines")
public class TrainingRoutineController {

    @Autowired
    private TrainingRoutineService service;

    @Operation(summary = "Get all training routines from all users", description = "Get all training routines from all users with pagination")
    @ApiResponse(responseCode = "200", description = "Found all training routines from all users", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TrainingRoutineDTO.class))) })
    @GetMapping(params = { "page", "size" })
    public List<TrainingRoutineDTO> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size) {
        List<TrainingRoutineDTO> trainingRoutines = service.getAll(page, size);
        return trainingRoutines;
    }

    @Operation(summary = "Get training routines by user ID")
    @ApiResponse(responseCode = "200", description = "Found training routines for the specified user", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TrainingRoutineDTO.class))) })
    @GetMapping("/user/{userId}")
    public List<TrainingRoutineDTO> getByUserId(@RequestParam Integer id) {
        return service.getByUserId(id);
    }

    // @Operation(summary = "Save a training routine")
    // @ApiResponses( value ={
    // @ApiResponse(responseCode = "200", description = "Training routine saved
    // successfully" ,content = { @Content(mediaType = "application/json" , schema =
    // @Schema(implementation = TrainingRoutine.class)) }),
    // @ApiResponse(responseCode = "400", description = "Bad request")
    // }
    // )
    // @PostMapping
    // public ResponseEntity<?> save(TrainingRoutine trainingRoutine) {
    // service.save(trainingRoutine);
    // return new ResponseEntity<>("Training routine saved successfully",
    // HttpStatus.OK);
    // }

    @Operation(summary = "Save a training routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training routine saved successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TrainingRoutineRequestDTO.class))
            })
    })
    @PostMapping
    public ResponseEntity<?> save(TrainingRoutineRequestDTO trainingRoutineRequestDTO) {
        service.save(trainingRoutineRequestDTO);
        return new ResponseEntity<>("Training routine saved successfully", HttpStatus.OK);
    }
}
