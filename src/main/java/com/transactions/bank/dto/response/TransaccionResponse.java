package com.transactions.bank.dto.response;

import com.transactions.bank.domain.EstadoTransaccion;
import com.transactions.bank.domain.TipoTransaccion;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransaccionResponse {

    private Long id;
    private TipoTransaccion tipoTransaccion;
    private BigDecimal monto;
    private EstadoTransaccion  estadoTransaccion;
    private LocalDate fecha;
    private Long CuentaOrigenId;
    private Long CuentaDestinoId;
}
