package com.transactions.bank.infrastructure.web.controller;

import com.transactions.bank.application.dto.AuthRequest;
import com.transactions.bank.application.dto.AuthResponse;
import com.transactions.bank.application.dto.UsuarioRequest;
import com.transactions.bank.application.services.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registrarUsuario(@Valid @RequestBody UsuarioRequest request) {
        AuthResponse respuesta = authService.registrarUsuario(request);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registrar(@Valid @RequestBody UsuarioRequest request) {
        AuthResponse respuesta = authService.registrarUsuario(request);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> autenticarUsuario(@Valid @RequestBody AuthRequest request) {
        AuthResponse respuesta = authService.autenticarUsuario(request);
        return ResponseEntity.ok(respuesta);
    }
}