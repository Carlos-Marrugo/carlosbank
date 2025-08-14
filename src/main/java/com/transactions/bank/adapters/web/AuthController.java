package com.transactions.bank.adapters.web;

import com.transactions.bank.dto.request.AuthRequest;
import com.transactions.bank.dto.response.AuthResponse;
import com.transactions.bank.dto.request.UsuarioRequest;
import com.transactions.bank.services.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> autenticarUsuario(@Valid @RequestBody AuthRequest request) {
        AuthResponse respuesta = authService.autenticarUsuario(request);
        return ResponseEntity.ok(respuesta);
    }
}