package com.ghostappi.backend.controller;

import java.util.List;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Categories", description = "Methods required to manage categories")
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
    @GetMapping("{idCategory}")
    public Category getById(@RequestParam Integer id) {
        return service.getById(id);
    }

    @PutMapping("{idCategory}")
    public ResponseEntity<Category> update(@PathVariable Integer idCategory, @RequestBody Category category) {
        if (!Objects.equals(category.getIdCategory(), idCategory)) {
            throw new IllegalArgumentException("The provider identifiers do not match");
        }
        Category existingCategory = service.getById(idCategory);
        existingCategory.setName(category.getName());
        service.save(existingCategory);
        return new ResponseEntity<>(existingCategory,HttpStatus.OK);

    }
}
