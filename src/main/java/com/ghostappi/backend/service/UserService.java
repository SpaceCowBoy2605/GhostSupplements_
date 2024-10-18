package com.ghostappi.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.ghostappi.backend.model.User;
import com.ghostappi.backend.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService 
{
    @Autowired
    private  UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void save (User user){
        userRepository.save(user);
    }

    public List<User> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<User> users = userRepository.findAll(pageReq);
        return users.getContent();
    }

    public User getUserIdUser(Integer idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + idUser));
    }
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteById(Integer idUser) {
        userRepository.deleteById(idUser);
    }

}
