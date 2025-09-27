package com.transactions.bank.application.services;

import com.transactions.bank.application.dto.request.CuentaRequest;
import com.transactions.bank.application.dto.response.CuentaResponse;
import com.transactions.bank.application.port.in.CuentaUseCase;
import com.transactions.bank.application.port.out.CuentaRepositoryPort;
import com.transactions.bank.domain.exceptions.CuentaExisteException;
import com.transactions.bank.domain.model.Cuenta;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuentaService implements CuentaUseCase{

    @Autowired
    private CuentaRepositoryPort cuentaRepositoryPort;

    @Override
    public CuentaRequest crearCuenta(CuentaRequest cuentaDto) {
        if (cuentaRepositoryPort.findByNumeroCuenta(cuentaDto.getNumeroCuenta()).isPresent()) {
            throw new CuentaExisteException("La cuenta ya existe: " + cuentaDto.getNumeroCuenta());
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
        cuenta.setPropietario(cuentaDto.getPropietario());
        cuenta.setSaldo(cuentaDto.getSaldo());
        cuenta.setFechaCreacion(LocalDateTime.now());

        Cuenta cuentaGuardada = cuentaRepositoryPort.save(cuenta);

        return convertirADto(cuentaGuardada);
    }

    @Override
    public List<CuentaRequest> listarTodasLasCuentas() {
        return cuentaRepositoryPort.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
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

    @Override
    public CuentaRequest obtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + id));
        return convertirADto(cuenta);
    }

    private CuentaRequest convertirADto(Cuenta cuenta) {
        return new CuentaRequest(
                cuenta.getId(),
                cuenta.getNumeroCuenta(),
                cuenta.getPropietario(),
                cuenta.getSaldo(),
                cuenta.getFechaCreacion()
        );
    }
}
