package com.transactions.bank.infrastructure.persistence.jpa;

import com.transactions.bank.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaJpaRepository extends JpaRepository<CuentaEntity, Long> {
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
}