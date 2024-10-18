package com.ghostappi.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.repository.ProductRepository;
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
    
}
