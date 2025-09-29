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
        cuentaDto.setSaldo(request.getSaldoInicial());

        CuentaDto saveDto = new cuentaUserCase.crearCuenta(cuentaDto);
        CuentaResponse response = toResponse(saveDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponse>> listarCuentas() {
        List<CuentaResponse> cuentas = cuentaService.listarTodasLasCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponse> obtenerCuenta(@PathVariable Long id) {
        CuentaResponse cuenta = cuentaService.obtenerCuentaPorId(id);
        return ResponseEntity.ok(cuenta);
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
