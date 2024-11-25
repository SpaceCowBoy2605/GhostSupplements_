package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.model.Reward;
import com.ghostappi.backend.service.RewardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rewards")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Rewards", description = "Provides methods for managing rewards")
public class RewardController {
    @Autowired
    private RewardService rew;

    @Operation(summary = "Get all rewards with pagination")
    @GetMapping
    public ResponseEntity<List<Reward>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Reward> rewards = rew.getAll(pageable);

        return new ResponseEntity<>(rewards.getContent(), HttpStatus.OK);
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
        })
    })
    @GetMapping("{idReward}")
    public ResponseEntity<?> getIdReward(@PathVariable Integer idReward) {
        Reward reward = rew.getIdReward(idReward);
        return new ResponseEntity<>(reward, HttpStatus.OK);
    }

    // post
    @Operation(summary = "Register a new Reward")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The request has been successful and the product has been successfully created.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))
        }),
        @ApiResponse(responseCode = "400", description = "Please verify the data entered and try again.", content = {
            @Content
        })
    })
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody Reward rews) {
        rew.save(rews);
        return new ResponseEntity<>("Reward add correctly", HttpStatus.OK);
    }

    // update
    @Operation(summary = "Update an existing reward")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))
        }),
            @ApiResponse(responseCode = "404", description = "reward with ID not found")
    })
    @PutMapping("{idReward}")
    public ResponseEntity<?> update(@RequestBody Reward reward, @Valid @PathVariable Integer idReward) {
        Reward existingPro = rew.getIdReward(idReward);
        reward.setIdReward(existingPro.getIdReward());
        rew.save(reward);
        return new ResponseEntity<>("Updated record", HttpStatus.OK);
    }

    // delete
    @Operation(summary = "Delete a reward by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "The reward has been successfully deleted."),
        @ApiResponse(responseCode = "404", description = "Product with ID not found."),
    })
    @DeleteMapping("{idReward}")
    public ResponseEntity<?> delete(@PathVariable Integer idReward) {
        Reward reward = rew.getIdReward(idReward);
        if (reward == null) {
            return new ResponseEntity<>("Reward with ID not found.", HttpStatus.NOT_FOUND);
        }
        rew.delete(idReward);
        return new ResponseEntity<>("Reward deleted successfully", HttpStatus.OK);
    }
}
