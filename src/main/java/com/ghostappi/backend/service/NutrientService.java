package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Nutrient;
import com.ghostappi.backend.repository.NutrientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NutrientService {

    @Autowired
    private NutrientRepository nutrientRepository;
    
    public List<Nutrient> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Nutrient> pageResult = nutrientRepository.findAll(pageRequest);
        return pageResult.getContent();
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