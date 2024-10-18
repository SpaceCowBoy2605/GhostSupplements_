package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Category;
import com.ghostappi.backend.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll(){
        return repository.findAll();
    }
    
    public void save(Category category){
        repository.save(category);
    }

    public Category getById(Integer id){
        return  repository.findById(id).get();
    }

    // public List<Category>getByCategoryName(String name){
    //     List<Category> categories = repository.findBy(null, null);
    // }
}
