package com.transactions.bank.application.services;

import com.transactions.bank.domain.model.Usuario;
import com.transactions.bank.infrastructure.persistence.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = Usuario.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .build();
            java.util.Objects.requireNonNull(admin);
            usuarioRepository.save(admin);
        }
    }
}