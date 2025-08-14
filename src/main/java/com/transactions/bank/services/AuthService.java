package com.transactions.bank.services;

import com.transactions.bank.config.JwtTokenProvider;
import com.transactions.bank.domain.Usuario;
import com.transactions.bank.dto.request.AuthRequest;
import com.transactions.bank.dto.response.AuthResponse;
import com.transactions.bank.dto.request.UsuarioRequest;
import com.transactions.bank.adapters.persistence.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse registrarUsuario(UsuarioRequest request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        usuarioRepository.save(usuario);

        return new AuthResponse(
                "Usuario registrado exitosamente. Inicie sesión para obtener un token.",
                null
        );
    }

    public AuthResponse autenticarUsuario(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtTokenProvider.generarToken(authentication);

        return new AuthResponse(
                "Autenticación exitosa",
                token
        );
    }
}