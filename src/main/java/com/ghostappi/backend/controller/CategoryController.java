package com.ghostappi.backend.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.model.Category;
import com.ghostappi.backend.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*", methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE })
@Tag(name = "Categories", description = "Provides methods for managing categories")
public class CategoryController {

        @Autowired
        private CategoryService service;

        @Operation(summary = "Get all categories with pagination", description = "Return a list of all categories with pagination")
        @ApiResponse(responseCode = "200", description = "Found all categories", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))) })
        @GetMapping(params = { "page", "size" })
        public List<Category> getAll(
                        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                        @RequestParam(value = "size", defaultValue = "5", required = false) int size
        ) {
                List<Category> categories = service.getAll(page, size);
                return categories;
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

        @Operation(summary = "Update a category by id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Category found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
                        @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                        @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
        })
        @PutMapping("{idCategory}")
        public ResponseEntity<Category> update(@PathVariable Integer idCategory, @RequestBody Category category) {
                if (!Objects.equals(category.getIdCategory(), idCategory)) {
                        throw new IllegalArgumentException("The provider identifiers do not match");
                }
                Category existingCategory = service.getById(idCategory);
                existingCategory.setName(category.getName());
                service.save(existingCategory);
                return new ResponseEntity<>(existingCategory, HttpStatus.OK);

        }
}
