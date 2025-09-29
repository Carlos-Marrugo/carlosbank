package com.transactions.bank.infrastructure.web.controller;

import com.transactions.bank.application.dto.CuentaDto;
import com.transactions.bank.application.dto.request.CuentaRequest;
import com.transactions.bank.application.dto.response.CuentaResponse;
import com.transactions.bank.application.port.in.CuentaUseCase;
import com.transactions.bank.application.services.CuentaService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/cuentas")
public class CuentaController {

    private final CuentaUseCase cuentaUseCase;

    public CuentaController(CuentaUseCase cuentaUseCase) {
        this.cuentaUseCase = cuentaUseCase;
    }

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@Valid @RequestBody CuentaRequest request) {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setNumeroCuenta(request.getNumeroCuenta());
        cuentaDto.setPropietario(request.getPropietario());
        cuentaDto.setSaldo(request.getSaldoInicial());

        CuentaDto savedDto = cuentaUseCase.crearCuenta(cuentaDto);
        CuentaResponse response = toResponse(savedDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponse>> listarCuentas() {
        List<CuentaDto> cuentas = cuentaUseCase.listarTodasLasCuentas();
        List<CuentaResponse> responses = cuentas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponse> obtenerCuenta(@PathVariable Long id) {
        CuentaDto cuenta = cuentaUseCase.obtenerCuentaPorId(id);
        CuentaResponse response = toResponse(cuenta);
        return ResponseEntity.ok(response);
    }

    private CuentaResponse toResponse(CuentaDto dto) {
        return new CuentaResponse(
            dto.getId(),
            dto.getNumeroCuenta(),
            dto.getPropietario(),
            dto.getSaldo(),
            dto.getFechaCreacion()
        );
    }
}
