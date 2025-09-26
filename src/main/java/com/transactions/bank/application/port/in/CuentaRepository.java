package com.transactions.bank.application.port.in;

import java.util.List;
import java.util.Optional;

import com.transactions.bank.domain.model.Cuenta;

public interface CuentaRepository {
    Cuenta save(Cuenta cuenta);
    Optional<Cuenta> findById(Long id);
    Optional<Cuenta> findByNumeroDeCuenta(String numeroCuenta);
    List<Cuenta> findAll();
    void deleteById(Long id);
    
}
