package com.ghostappi.backend.controller;

import java.security.Provider;
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


import com.ghostappi.backend.model.Reward;
import com.ghostappi.backend.service.RewardService;

@RestController
@RequestMapping("/Reward")
@CrossOrigin(origins="*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name="Rewards")
public class RewardController {
    @Autowired
    private RewardService rew;

    @GetMapping
    public List<Reward> getAll(){
        return rew.getAll();
    }

     // get
        @Operation(summary = "Get reward by ID")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found reward", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))
        }),
        @ApiResponse(responseCode = "404", description = "reward not found, please verify your information"),
        @ApiResponse(responseCode = "400", description = "incorrectly entered data, verify the data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content
        })
    })

    @GetMapping("{idReward}") 
    public ResponseEntity<?>getIdReward(@PathVariable Integer idReward) {
        try {
            Reward reward = rew.getIdReward(idReward);
            return new ResponseEntity<>(reward, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Reward not found", HttpStatus.NOT_FOUND);
        }
        
    }

    // post
    @Operation(summary = "Register a new Reward")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "The request has been successful and the product has been successfully created.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))
        }),
        @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred. We are working to resolve the problem as soon as possible.", content = {
            @Content
        })
    })
    @PostMapping
    public ResponseEntity<?> register(@RequestBody Reward rews) {
        try {
            rew.save(rews);
            return new ResponseEntity<>("rEWARD add correctly", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Reward not add, verify data", HttpStatus.NOT_FOUND);
        }
    }


    //update 
    @Operation(summary = "Update an existing reward")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))
        }),
        @ApiResponse(responseCode = "404", description = "reward with ID not found"),
        @ApiResponse(responseCode = "400", description = "Invalid reward data", content = {
            @Content
        }),
        @ApiResponse(responseCode = "500", description = "An internal server error has occurred", content = {
            @Content
        })
    })
    @PutMapping("{idReward}")
    public ResponseEntity<?> update(@RequestBody Reward reward, @PathVariable Integer idReward) {
        try {
            Reward existingPro = rew.getIdReward(idReward);
            reward.setIdReward(existingPro.getIdReward());  // Ensure id is not overwritten
            rew.save(reward);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("information not found", HttpStatus.NOT_FOUND);
        }
       
    }

    //delete
    @Operation(summary = "Delete a reward by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "The reward has been successfully deleted."),
        @ApiResponse(responseCode = "404", description = "Product with ID not found."),
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
    @DeleteMapping("{idReward}")
    public ResponseEntity<?> delete(@PathVariable Integer idReward) {
        try { 
            Reward reward = rew.getIdReward(idReward);
            if(reward == null){
                return new ResponseEntity<>("Reward with ID not found.", HttpStatus.NOT_FOUND);
            }
            rew.delete(idReward);
            return new ResponseEntity<>("Reward deleted successfully", HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>("Reward with ID not found.", HttpStatus.NOT_FOUND); 
        }
        
    }
}
