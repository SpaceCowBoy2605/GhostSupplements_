package com.ghostappi.backend.controller;
import java.util.List;

// import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
// import io.swagger.v3.oas.annotations.media.ArraySchema;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ghostappi.backend.model.User;
import com.ghostappi.backend.service.UserService;
// import org.springframework.validation.annotation.Validated;


import jakarta.validation.Valid;


// @RestController
// @RequestMapping("users")
// @CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
//         RequestMethod.PUT })
// @Validated
// @Tag(name = "User Management", description = "Provides methods for managing users")
public class UserController {
    @Autowired
    private UserService service;

    // @Operation(summary = "Get all ")
    // @ApiResponse(responseCode = "200", description = "Found Users", content = {
    //         @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))) })
    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    // @Operation(summary = "Get a user by its ID")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "User found", content = {
    //                 @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
    //         @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
    //         @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @GetMapping("{idUser}")
    public ResponseEntity<?> getByIdUser(@PathVariable Integer idUser) {
        try {
            User user = service.getUserIdUser(idUser);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // @Operation(summary = "Register a user")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "201", description = "User saved", content = @Content),
    //         @ApiResponse(responseCode = "400", description = "Invalid user information", content = @Content),
    //         @ApiResponse(responseCode = "409", description = "Email already exists", content = @Content),
    //         @ApiResponse(responseCode = "500", description = "Error saving record", content = @Content) })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody User user) { // Agrega @Valid aquí
        try {
            if (service.emailExists(user.getEmail())) {
                return new ResponseEntity<String>("Email already exists", HttpStatus.CONFLICT);
            }
            service.save(user);
            return new ResponseEntity<String>("Saved record", HttpStatus.CREATED); // Cambié a 201 para un nuevo recurso
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error saving record: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @Operation(summary = "Delete a user by its ID")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "User deleted", content = @Content),
    //         @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer idUser) {
        try {
            service.deleteById(idUser);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // @Operation(summary = "Get all users with pagination")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "Found users", content = {
    //                 @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    //         }),
    //         @ApiResponse(responseCode = "400", description = "Invalid page or size supplied", content = @Content),
    //         @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
    // })
    @GetMapping(value = "pagination", params = { "page", "size" })
    public ResponseEntity<List<User>> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {

        List<User> users = service.getAll(page, pageSize);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    }


