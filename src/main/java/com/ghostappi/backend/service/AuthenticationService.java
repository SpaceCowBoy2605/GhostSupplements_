package com.ghostappi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.dto.UserDTO;
import com.ghostappi.backend.dto.UserLoginDTO;
import com.ghostappi.backend.model.User;
import com.ghostappi.backend.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User signup(UserDTO userDTO) {
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setGender(userDTO.getGender());
        user.setStatus(userDTO.getStatus());
        user.setBornDate(userDTO.getBornDate());
        user.setIsCostumer(userDTO.getIsCostumer());
        return userRepository.save(user);
    }

    //passwordEncoder.encode

    public User authenticate(UserLoginDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(),
                        userDTO.getPassword()
                )
        );

        return userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow();
    }
}