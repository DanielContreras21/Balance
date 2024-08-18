package com.app.balance.controller;

import com.app.balance.model.request.LoginRequest;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.response.AuthResponse;
import com.app.balance.model.response.LoginResponse;
import com.app.balance.service.abstraction.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest register){
        AuthResponse user = service.register(register);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/registerSuperUser")
    public ResponseEntity<AuthResponse> registerSuperUser(@Valid @RequestBody RegisterRequest register){
        AuthResponse user = service.registerSuperUser(register);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login){
        LoginResponse user = service.login(login);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
 }
