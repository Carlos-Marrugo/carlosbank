package com.transactions.bank.application.port.in;

import java.util.List;

import com.transactions.bank.application.dto.request.TransaccionRequest;

public interface TransaccionUseCase {
    TransaccionRequest crearTransaccion(TransaccionRequest transaccionRequest);
    TransaccionRequest obtenerTransaccionPorId(Long id);
    List<TransaccionRequest> listarTransaccionesPorCuenta(Long cuentaId);
}
