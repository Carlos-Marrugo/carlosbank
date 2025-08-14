package com.transactions.bank.dto.response;

import com.transactions.bank.domain.EstadoTransaccion;
import com.transactions.bank.domain.TipoTransaccion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionResponse {
    private Long id;
    private TipoTransaccion tipo;
    private BigDecimal monto;
    private EstadoTransaccion estado;
    private LocalDateTime fecha;
    private Long cuentaOrigenId;
    private Long cuentaDestinoId;
}