package com.transactions.bank.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.transactions.bank.domain.model.TipoTransaccion;

@Data
public class TransaccionRequest {
    @NotNull(message = "El tipo de transaccion es obligatorio")
    private TipoTransaccion tipo;

    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal monto;
    private LocalDateTime fecha;

    private Long cuentaOrigenId;
    private Long cuentaDestinoId;


}
