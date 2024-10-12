package com.ghostappi.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.ghostappi.backend.model.User;
import com.ghostappi.backend.service.UserService;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {
   private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Page<User> getAll(Pageable pageable) {
        return service.getAll( pageable);
    }

    //GET
    @Operation(summary = "Get card by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found user correctly", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
        }),
        @ApiResponse(responseCode = "404", description = "User not found, please verify your information"),
        @ApiResponse(responseCode = "400", description = "incorrectly entered data, verify the data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content
        })
    })

    @GetMapping("{idUser}")
    public ResponseEntity<User> getIdUser(@PathVariable Integer idUser) {
    User user = service.getIdUser(idUser);
    return new ResponseEntity<>(user,  HttpStatus.OK);
    }
    
    //post
    @Operation(summary = "Register user")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "The request has been successful and the card has been successfully add.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    }),
    @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
        @Content
    }),
    @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
        @Content
    })
})
@PostMapping
  public void register(@RequestBody User user) {
    service.save(user);
  }
//update
@Operation(summary = "Update an existing user")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Updated record", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    }),
    @ApiResponse(responseCode = "404", description = "user with ID not found"),
    @ApiResponse(responseCode = "400", description = "Invalid user data", content = {
        @Content
    }),
    @ApiResponse(responseCode = "500", description = "An internal server error has occurred", content = {
        @Content
    })
})
@PutMapping("{idUser}")
public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer idUser) {
    User existingUser = service.getIdUser(idUser);
    user.setIdUser(existingUser.getIdUser());  
    service.save(user);
    return new ResponseEntity<String>("Updated record", HttpStatus.OK);
}

//delete
@Operation(summary = "Delete a wallet by ID")
@ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "The user has been successfully deleted."),
    @ApiResponse(responseCode = "404", description = "User with ID not found."),
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
  @DeleteMapping("{idUser}")
  public void delete(@PathVariable Integer idUser) {
    service.delete(idUser);
  }

}
