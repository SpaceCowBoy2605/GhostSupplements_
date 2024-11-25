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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.model.Points;
import com.ghostappi.backend.service.PointsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/points")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT })
@Tag(name = "Points", description = "Provides methods for managing points")
public class PointsController {
        @Autowired
        private PointsService poinser;

        @Operation(summary = "Get all points with pagination", description = "Return a list of all points with pagination")
        @GetMapping(params = { "page", "size" })
        public List<Points> getAll(
                        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                        @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
                List<Points> points = poinser.getAll(page, size);
                return points;
        }

        // GET
        @Operation(summary = "Get points by ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Found points correctly", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Points.class))
                }),
                        @ApiResponse(responseCode = "404", description = "Points not found, please verify your information"),
        })
        @GetMapping("{idPoint}")
        public ResponseEntity<?> getIdPoint(@PathVariable Integer idPoint) {
                Points points = poinser.getIdPoint(idPoint);
                return new ResponseEntity<>(points, HttpStatus.OK);
        }

        // post
        @Operation(summary = "Register points")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "The request has been successful and the points has been successfully add.", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Points.class))
                }),
                        @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
                        @Content
                })
        })
        @PostMapping
        public ResponseEntity<?> register(@RequestBody Points points) {
                if (points.getUserId() == null) {
                        return new ResponseEntity<>("userId cannot be null", HttpStatus.BAD_REQUEST);
                }
                poinser.save(points);
                return new ResponseEntity<>("Points added correctly", HttpStatus.OK);
        }

        // update
        @Operation(summary = "Update an existing points")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Updated record", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Points.class))
                        }),
                        @ApiResponse(responseCode = "404", description = "points with ID not found")
        })
        @PutMapping("{idPoint}")
        public ResponseEntity<?> update(@RequestBody Points points, @PathVariable Integer idPoint) {
                Points existingPoint = poinser.getIdPoint(idPoint);
                points.setIdPoints(existingPoint.getIdPoints());
                poinser.save(points);
                return new ResponseEntity<>("Updated record", HttpStatus.OK);
        }

        // delete
        @Operation(summary = "Delete a points by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "The points has been successfully deleted."),
                        @ApiResponse(responseCode = "404", description = "Wallet with ID not found."),
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
