package com.transactions.bank.infrastructure.web.controller;

import com.transactions.bank.application.dto.request.TransaccionRequest;
import com.transactions.bank.application.dto.response.TransaccionResponse;
import com.transactions.bank.application.services.TransaccionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransactionsController {

    private final TransaccionService transaccionService;

    @Autowired
    public TransactionsController(TransaccionService  transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping
    public ResponseEntity<TransaccionResponse> crearTransaccion(@Valid @RequestBody TransaccionRequest request) {
        TransaccionResponse response = transaccionService.iniciarTransaccion(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionResponse> obtenerTransaccionPorId(@PathVariable Long id) {
        java.util.Objects.requireNonNull(id);
        TransaccionResponse response = transaccionService.obtenerTransaccionPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-cuenta/{cuentaId}")
    public ResponseEntity<List<TransaccionResponse>> obtenerTransaccionesPorCuenta(@PathVariable Long cuentaId) {
        List<TransaccionResponse> responses = transaccionService.obtenerTransaccionesPorCuenta(cuentaId);
        return ResponseEntity.ok(responses);
    }
}
