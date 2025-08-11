package com.transactions.bank.adapters.persistence;

import com.transactions.bank.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findByNumeroCuenta(String numeroCuenta);
}
