package com.transactions.bank.adapters.web;

import com.transactions.bank.dto.request.TransaccionRequest;
import com.transactions.bank.dto.response.TransaccionResponse;
import com.transactions.bank.services.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
