package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.TransactionScoped;

import com.ghostappi.backend.model.Excercise;
import com.ghostappi.backend.repository.ExcerciseRepository;

@Service
@TransactionScoped
public class ExcerciseService {

    @Autowired
    private ExcerciseRepository excerciseRepository;

    public List<Excercise> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Excercise> pageResult = excerciseRepository.findAll(pageRequest);
        return pageResult.getContent();
    }

    public Excercise getById (Integer id) {
        return excerciseRepository.findById(id).get();
    }

    public Excercise save (Excercise excercise) {
        return excerciseRepository.save(excercise);
    }

    public void delete (Integer id) {
        excerciseRepository.deleteById(id);
    }
}
