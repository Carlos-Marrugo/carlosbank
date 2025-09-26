package com.transactions.bank.application.port.out;

import java.util.List;
import java.util.Optional;

import com.transactions.bank.domain.model.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findAll();
    void deleteById(Long id);

}