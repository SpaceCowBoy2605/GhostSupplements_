package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Nutrient;
import com.ghostappi.backend.repository.NutrientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NutrientService {

    @Autowired
    private NutrientRepository nutrientRepository;
    
    public List<Nutrient> getAll() {
        return nutrientRepository.findAll();
    }

    public Nutrient save (Nutrient nutrient) {
        return nutrientRepository.save(nutrient);
    }

    public Nutrient getById (Integer id) {
        return nutrientRepository.findById(id).get();
    }

    public void delete (Integer id) {
        nutrientRepository.deleteById(id);
    }

}