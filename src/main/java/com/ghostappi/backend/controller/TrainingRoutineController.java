package com.ghostappi.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ghostappi.backend.dto.TrainingRoutineDTO;
import com.ghostappi.backend.model.TrainingRoutine;
import com.ghostappi.backend.service.TrainingRoutineService;

@RestController
@RequestMapping("/trainingroutines")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "TrainingRoutines", description = "Provides a methods for managing training routines from the database")
public class TrainingRoutineController {

    @Autowired
    private TrainingRoutineService service;

    @Operation(summary = "Get all training routines from all users")
    @ApiResponse(responseCode = "200", description = "Found all training routines from all users",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TrainingRoutineDTO.class))) })
    @GetMapping
    public List<TrainingRoutineDTO> getAll() {
        return service.getAll();
    }
}
