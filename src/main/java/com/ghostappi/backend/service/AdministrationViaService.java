package com.ghostappi.backend.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.AdministrationVia;
import com.ghostappi.backend.repository.AdministrationViaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdministrationViaService {

    @Autowired
    private AdministrationViaRepository administrationViaRepository;
    
    public List<AdministrationVia> getAll(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<AdministrationVia> administrationVias = administrationViaRepository.findAll(pageable);
        return administrationVias.getContent();
    }

    public AdministrationVia save (AdministrationVia administrationVia) {
        return administrationViaRepository.save(administrationVia);
    }

    public AdministrationVia getById (Integer id) {
        return administrationViaRepository.findById(id).get();
    }

    public void delete (Integer id) {
        administrationViaRepository.deleteById(id);
    }
}
