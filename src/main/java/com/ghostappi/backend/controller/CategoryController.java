package com.ghostappi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import com.ghostappi.backend.service.CategoryService;
import com.ghostappi.backend.model.Category;

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Category", description = "APIs for managing categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Operation(summary = "List all categories")
    @ApiResponse(responseCode = "200", description = "Found all categories", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))) })
    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Create a new a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Nutrient not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void save(Category category) {
        service.save(category);
    }

    @Operation(summary = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("{id}")
    public Category getById(@RequestParam Integer id) {
        return service.getById(id);
    }

    
}
