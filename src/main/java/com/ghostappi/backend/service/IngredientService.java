package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.repository.IngredientRepository;
import com.ghostappi.backend.model.Ingredient;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Ingredient> pageResult = ingredientRepository.findAll(pageRequest);
        return pageResult.getContent();
    }

    public Ingredient save (Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient getById (Integer id) {
        return ingredientRepository.findById(id).get();
    }

    public void delete (Integer id) {
        ingredientRepository.deleteById(id);
    }
}
