package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.NutrientProduct;
import com.ghostappi.backend.repository.NutrientProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NutrientProductService {

    @Autowired
    private NutrientProductRepository repository;

    public List<NutrientProduct> getAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<NutrientProduct> pageResult = repository.findAll(pageRequest);
        return pageResult.getContent();
    }
    
    public void save(NutrientProduct nutrientProduct){
        repository.save(nutrientProduct);
    }
}
