package com.ghostappi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghostappi.backend.dto.UserDTO;
import com.ghostappi.backend.dto.UserLoginDTO;
import com.ghostappi.backend.model.User;
import com.ghostappi.backend.response.LoginResponse;
import com.ghostappi.backend.service.AuthenticationService;
import com.ghostappi.backend.util.JwtService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationService authenticationService;

@PostMapping("/signup")
public ResponseEntity<String> register(@RequestBody UserDTO userDto) {
    authenticationService.signup(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
}

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLoginDTO userLoginDto) {
        User authenticatedUser = authenticationService.authenticate(userLoginDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
