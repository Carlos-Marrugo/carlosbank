package com.transactions.bank.infrastructure.mesagging.adapter;

import java.util.List;
import java.util.Optional;

import com.transactions.bank.application.port.in.CuentaRepository;
import com.transactions.bank.application.port.out.CuentaRepositoryPort;
import com.transactions.bank.domain.model.Cuenta;

public class CuentaRepositoryAdapter implements CuentaRepositoryPort {

    @Override
    public Cuenta save(Cuenta cuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNumeroCuenta'");
    }

    @Override
    public List<Cuenta> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}
