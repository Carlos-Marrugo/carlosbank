package com.transactions.bank.application.port.in;

import java.util.List;

import com.transactions.bank.application.dto.request.CuentaRequest;

public interface CuentaUseCase {
    CuentaRequest crearCuenta(CuentaRequest cuentaRequest);
    List<CuentaRequest> listarTodasLasCuentas();
    CuentaRequest obtenerCuentaPorId(Long id);
}
