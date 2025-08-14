package com.transactions.bank.services;

import com.transactions.bank.adapters.persistence.CuentaRepository;
import com.transactions.bank.domain.Cuenta;
import com.transactions.bank.domain.exceptions.CuentaExisteException;
import com.transactions.bank.dto.request.CuentaRequest;
import com.transactions.bank.dto.response.CuentaResponse;
import jakarta.transaction.Transactional;
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


    public List<CuentaResponse> listarTodasLasCuentas() {
        return cuentaRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public CuentaResponse obtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el cuenta con el id: " + id));
        return  convertirAResponse(cuenta);
    }



}
