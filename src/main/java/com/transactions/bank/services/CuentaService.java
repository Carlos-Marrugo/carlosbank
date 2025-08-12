package com.transactions.bank.services;

import com.transactions.bank.adapters.persistence.CuentaRepository;
import com.transactions.bank.domain.Cuenta;
import com.transactions.bank.domain.exceptions.CuentaExisteException;
import com.transactions.bank.dto.CuentaRequest;
import com.transactions.bank.dto.CuentaResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    private CuentaResponse convertirAResponse(Cuenta cuenta) {
        return new CuentaResponse(
                cuenta.getId(),
                cuenta.getNumeroCuenta(),
                cuenta.getPropietario(),
                cuenta.getSaldo(),
                cuenta.getFechaCreacion()
        );
    }

    @Transactional
    public CuentaResponse crearCuenta(CuentaRequest request) {
        if(cuentaRepository.findByNumeroCuenta(request.getNumeroCuenta())!=null){
            throw new CuentaExisteException("Esta cuenta ya esta registrada!");
        }

        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(request.getNumeroCuenta())
                .propietario(request.getPropietario())
                .saldo(request.getSaldo())
                .build();

        cuentaRepository.save(cuenta);

        return convertirAResponse(cuenta);
    }


}
