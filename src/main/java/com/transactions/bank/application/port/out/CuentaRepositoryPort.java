package com.transactions.bank.application.port.out;

import com.transactions.bank.domain.model.Cuenta;
import java.util.List;
import java.util.Optional;

public interface CuentaRepositoryPort {
    Cuenta save(Cuenta cuenta);
    Optional<Cuenta> findById(Long id);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    List<Cuenta> findAll();
    void deleteById(Long id);
}