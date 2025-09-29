package com.transactions.bank.infrastructure.persistence.adapters;

import com.transactions.bank.application.port.out.CuentaRepositoryPort;
import com.transactions.bank.domain.model.Cuenta;
import com.transactions.bank.infrastructure.persistence.mapper.CuentaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CuentaRepositoryAdapter implements CuentaRepositoryPort {

    private final CuentaJpaRepository cuentaJpaRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaRepositoryAdapter(CuentaJpaRepository cuentaJpaRepository, CuentaMapper cuentaMapper) {
        this.cuentaJpaRepository = cuentaJpaRepository;
        this.cuentaMapper = cuentaMapper;
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        var cuentaEntity = cuentaMapper.toEntity(cuenta);
        var savedEntity = cuentaJpaRepository.save(cuentaEntity);
        return cuentaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return cuentaJpaRepository.findById(id)
                .map(cuentaMapper::toDomain);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return cuentaJpaRepository.findByNumeroCuenta(numeroCuenta)
                .map(cuentaMapper::toDomain);
    }

    @Override
    public List<Cuenta> findAll() {
        return cuentaJpaRepository.findAll().stream()
                .map(cuentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        cuentaJpaRepository.deleteById(id);
    }
}