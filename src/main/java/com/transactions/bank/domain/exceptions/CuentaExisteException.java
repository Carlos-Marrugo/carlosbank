package com.transactions.bank.domain.exceptions;

public class CuentaExisteException extends RuntimeException {
    public CuentaExisteException(String message) {
        super(message);
    }
}
