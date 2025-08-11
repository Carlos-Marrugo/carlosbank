package com.transactions.bank.services;

import com.transactions.bank.adapters.persistence.UsuarioRepository;
import com.transactions.bank.domain.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Crear ADMIN (opcional)
    /*
        Usuario admin = new Usuario();
        admin.setUsername("carlos.dev");


        admin.setPassword(passwordEncoder.encode("carlos123"));

        usuarioRepository.save(admin);*/

    }
}