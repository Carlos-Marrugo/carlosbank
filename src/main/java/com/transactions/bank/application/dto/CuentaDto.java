package com.transactions.bank.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CuentaDto {
    private Long id;
    private String numeroCuenta;
    private String propietario;
    private BigDecimal saldo;
    private LocalDateTime fechaCreacion;

    public CuentaDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public CuentaDto(Long id, String numeroCuenta, String propietario, BigDecimal saldo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    
}