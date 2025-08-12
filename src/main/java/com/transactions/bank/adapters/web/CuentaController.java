package com.transactions.bank.adapters.web;

import com.transactions.bank.domain.Cuenta;
import com.transactions.bank.dto.CuentaRequest;
import com.transactions.bank.dto.CuentaResponse;
import com.transactions.bank.services.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@Valid @RequestBody CuentaRequest request) {
        CuentaResponse response = cuentaService.crearCuenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
