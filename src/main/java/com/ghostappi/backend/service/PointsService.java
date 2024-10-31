package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Points;
import com.ghostappi.backend.repository.PointsRepository;

import jakarta.transaction.Transactional;


    @Service
    @Transactional
    public class PointsService {
    @Autowired
    private PointsRepository poinser;

    public List<Points>getAll(){
      return poinser.findAll();  
    }

     public void save(Points points){
        poinser.save(points);
    }

    public Points getIdPoint(Integer idPoint){
       return poinser.findById(idPoint).get(); 
    }

    public void delete(Integer idPoint){
        poinser.deleteById(idPoint);
    }

}

