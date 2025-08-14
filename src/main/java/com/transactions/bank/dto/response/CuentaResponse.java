package com.transactions.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponse {
    private Long id;
    private String numeroCuenta;
    private String propietario;
    private BigDecimal saldo;
    private LocalDateTime fechaCreacion;
}