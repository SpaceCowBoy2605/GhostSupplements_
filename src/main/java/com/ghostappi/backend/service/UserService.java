package com.ghostappi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.User;
import com.ghostappi.backend.repository.UserRepository;
import jakarta.transaction.Transactional;


    @Service
    @Transactional
    public class UserService {
    @Autowired
    private UserRepository userpor;

      public Page<User> getAll(Pageable pageable) {
        return userpor.findAll(pageable);
    }

    public void save(User user){
        userpor.save(user);
    }

    public User getIdUser(Integer idUser){
        return userpor.findById(idUser).get();
    }

    public void delete(Integer idUser){
        userpor.deleteById(idUser);
    }
}
