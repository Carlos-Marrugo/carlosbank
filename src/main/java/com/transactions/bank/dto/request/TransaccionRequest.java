package com.transactions.bank.dto.request;

import com.transactions.bank.domain.TipoTransaccion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransaccionRequest {
    @NotNull(message = "El tipo de transaccion es obligatorio")
    private TipoTransaccion tipo;

    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal monto;

    private Long cuentaOrigenId;
    private Long cuentaDestinoId;


}
