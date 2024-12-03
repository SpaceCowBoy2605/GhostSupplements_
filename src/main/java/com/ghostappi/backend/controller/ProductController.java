package com.ghostappi.backend.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.model.Product;
import com.ghostappi.backend.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.PUT
})
@Tag(name = "Products", description = "Provides methods for managing products")

public class ProductController {

        @Autowired
        private ProductService productService;

        @Operation(summary = "Get all products with pagination", description = "Return a list of products with pagination")
        @ApiResponse(responseCode = "200", description = "Success", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
        })
        @GetMapping(params = { "page", "size" })
        public List<Product> getAll(
                        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                        @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
                List<Product> products = productService.getAll(page, size);
                return products;
        }

        @Operation(summary = "Get product by id", description = "Get product by id")

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
        })
        // @SecurityRequirement(name = "bearerAuth")
        @GetMapping("{idProduct}")
        public Product getById(@RequestParam Integer idProduct) {
                return productService.getById(idProduct);
        }

        @Operation(summary = "Create a new Product", description = "Create a new Product")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Product created", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
        })
        // @SecurityRequirement(name = "bearerAuth")
        @PostMapping
        public ResponseEntity<String> save(@RequestBody Product product) {
                productService.save(product);
                return new ResponseEntity<>("Product created", HttpStatus.CREATED);
        }

        @Operation(summary = "Update a product", description = "Update a product")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Product updated", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Nutrient not found", content = @Content)
        })
        // @SecurityRequirement(name = "bearerAuth")
        @PutMapping("{idProduct}")
        public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Integer idProduct) {
                if (!Objects.equals(product.getIdProduct(), idProduct)) {
                        throw new IllegalArgumentException("The provider identifiers do not match");
                }
                Product productToUpdate = productService.getById(idProduct);
                productToUpdate.setComercialName(product.getComercialName());
                productToUpdate.setPrice(product.getPrice());
                productToUpdate.setStock(product.getStock());
                productToUpdate.setServingSize(product.getServingSize());
                productToUpdate.setUnitServingSize(product.getUnitServingSize());
                productToUpdate.setServings(product.getServings());
                productToUpdate.setNetContent(product.getNetContent());
                productToUpdate.setUnitNetContent(product.getUnitNetContent());
                productToUpdate.setPresentation(product.getPresentation());
                productToUpdate.setDescription(product.getDescription());
                productToUpdate.setCaducity(product.getCaducity());
                productToUpdate.setLote(product.getLote());
                productToUpdate.setFlavor(product.getFlavor());
                productToUpdate.setProductRecomendation(product.getProductRecomendation());
                productToUpdate.setImgProductPath(product.getImgProductPath());

                productService.save(productToUpdate);
                return new ResponseEntity<>(productToUpdate, HttpStatus.OK);

        }
}
