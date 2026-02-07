package com.transactions.bank.application.services;

import com.transactions.bank.application.dto.request.CuentaRequest;
import com.transactions.bank.application.dto.response.CuentaResponse;
import com.transactions.bank.domain.exceptions.CuentaExisteException;
import com.transactions.bank.domain.model.Cuenta;
import com.transactions.bank.infrastructure.persistence.CuentaRepository;

import jakarta.transaction.Transactional;
import org.springframework.lang.NonNull;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if (request.getNumeroCuenta() == null || request.getNumeroCuenta().isBlank()) {
            request.setNumeroCuenta(generarNumeroCuenta());
        }
        if(cuentaRepository.findByNumeroCuenta(request.getNumeroCuenta())!=null){
            throw new CuentaExisteException("Esta cuenta ya esta registrada!");
        }

        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(request.getNumeroCuenta())
                .propietario(request.getPropietario())
                .saldo(request.getSaldo())
                .build();

        Objects.requireNonNull(cuenta);
        cuentaRepository.save(cuenta);

        return convertirAResponse(cuenta);
    }


    public List<CuentaResponse> listarTodasLasCuentas() {
        return cuentaRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public CuentaResponse obtenerCuentaPorId(@NonNull Long id) {
        Objects.requireNonNull(id);
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el cuenta con el id: " + id));
        return  convertirAResponse(cuenta);
    }

    private String generarNumeroCuenta() {
        return "ACC-" + System.currentTimeMillis();
    }

}
