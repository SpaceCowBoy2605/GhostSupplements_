package com.ghostappi.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.repository.ProductRepository;
import com.ghostappi.backend.dto.ProductDTO;
import com.ghostappi.backend.model.Product;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save (Product product) {
        return productRepository.save(product);
    }

    public Product getById (Integer id) {
        return productRepository.findById(id).get();
    }

    public void delete (Integer id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setIdProduct(product.getIdProduct());
            dto.setComercialName(product.getComercialName());
            dto.setPrice(product.getPrice());
            dto.setStock(product.getStock());
            dto.setServingSize(product.getServingSize());
            dto.setUnitServingSize(product.getUnitServingSize());
            dto.setServings(product.getServings());
            // dto.setBrand(product.getBrand());
            // dto.setCategory(product.getCategory());
            // dto.setIngredients(product.getIngredients());
            // dto.setNutrientProducts(product.getNutrientProducts());
            // dto.setAdministrationVia(product.getAdministrationVia());
            return dto;
        }).collect(Collectors.toList());
    }
    
}
