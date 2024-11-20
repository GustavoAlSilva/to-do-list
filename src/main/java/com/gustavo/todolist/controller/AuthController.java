package com.gustavo.todolist.controller;

import com.gustavo.todolist.dto.LoginRequest;
import com.gustavo.todolist.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(401).body("Invalid username and/or password");
    }
}
