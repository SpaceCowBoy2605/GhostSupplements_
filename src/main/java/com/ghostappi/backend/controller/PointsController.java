package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ghostappi.backend.service.PointsService;
import com.ghostappi.backend.model.Points;



@RestController
@RequestMapping("/Points")
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name="Points")
public class PointsController {
    @Autowired
    private PointsService poinser;

    @GetMapping
    public List<Points>getAll(){
        return poinser.getAll();
    }

     //GET
    @Operation(summary = "Get points by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found points correctly", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Points.class))
        }),
        @ApiResponse(responseCode = "404", description = "Points not found, please verify your information"),
        @ApiResponse(responseCode = "400", description = "incorrectly entered data, verify the data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "Internal server error, please stand by", content = {
            @Content                    
        })
    })

    @GetMapping("{idPoint}")
    public ResponseEntity<?>getIdPoint(@PathVariable Integer idPoint) 
    {
        try {
            Points points = poinser.getIdPoint(idPoint);
            return new ResponseEntity<>(points, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Points not found", HttpStatus.NOT_FOUND);
        }
        
    }
    //post
    @Operation(summary = "Register points")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The request has been successful and the points has been successfully add.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Points.class))
        }),
        @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
            @Content
        })
    })
    @PostMapping
    public ResponseEntity<?> register(@RequestBody Points points) {
        try {
            poinser.save(points);
            return new ResponseEntity<>("Points added correctly", HttpStatus.OK); // Cambiado a 201
        } catch (Exception e) {
            return new ResponseEntity<>("points not added, verify data", HttpStatus.BAD_REQUEST);
        }
    }
   //update
    @Operation(summary = "Update an existing points")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Points.class))
        }),
        @ApiResponse(responseCode = "404", description = "points with ID not found"),
        @ApiResponse(responseCode = "400", description = "Invalid points data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred", content = {
            @Content
        })
    })
    @PutMapping("{idPoint}")
    public ResponseEntity<?> update(@RequestBody Points points, @PathVariable Integer idPoint) {
        try {
            Points existingPoint = poinser.getIdPoint(idPoint);
            points.setIdPoints(existingPoint.getIdPoints());  // Ensure id is not overwritten
            poinser.save(points);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Updated no record, verify data", HttpStatus.BAD_REQUEST);
        }
        
    }
        //delete
    @Operation(summary = "Delete a points by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The points has been successfully deleted."),
        @ApiResponse(responseCode = "404", description = "Wallet with ID not found."),
        @ApiResponse(responseCode = "400", description = "Invalid wallet ID.", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
            @Content
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized. Please log in and try again.", content = {
            @Content
        }),
        @ApiResponse(responseCode = "403", description = "Forbidden. You do not have permission to perform this action.", content = {
            @Content
        })
    })
    @DeleteMapping("{idPoint}")
    public ResponseEntity<?> delete(@PathVariable Integer idPoint) {
        if (poinser.getIdPoint(idPoint) == null) {
            return new ResponseEntity<>("Points not found", HttpStatus.NOT_FOUND);
        }
        poinser.delete(idPoint);
        return new ResponseEntity<>("Points deleted successfully", HttpStatus.OK);
    }


}
