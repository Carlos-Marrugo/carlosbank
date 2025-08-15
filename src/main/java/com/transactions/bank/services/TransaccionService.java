package com.transactions.bank.services;

import com.transactions.bank.adapters.persistence.CuentaRepository;
import com.transactions.bank.adapters.persistence.TransaccionRepository;
import com.transactions.bank.domain.*;
import com.transactions.bank.domain.exceptions.CuentaNoEncontradaException;
import com.transactions.bank.domain.exceptions.LimiteDiarioExcedidoException;
import com.transactions.bank.domain.exceptions.SaldoInsuficienteException;
import com.transactions.bank.dto.request.TransaccionRequest;
import com.transactions.bank.dto.response.TransaccionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    public TransaccionService(
            TransaccionRepository transaccionRepository,
            CuentaRepository cuentaRepository,
            RabbitTemplate rabbitTemplate) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaRepository = cuentaRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public TransaccionResponse iniciarTransaccion(TransaccionRequest request) {
        validarTransaccion(request);

        Transaccion transaccion = crearTransaccionPendiente(request);
        transaccionRepository.save(transaccion);

        rabbitTemplate.convertAndSend(exchangeName, routingKey, transaccion.getId());

        return convertirAResponse(transaccion);
    }

    private void validarTransaccion(TransaccionRequest request) {
        if (request.getTipo() == TipoTransaccion.TRANSFERENCIA || request.getTipo() == TipoTransaccion.RETIRO) {
            Cuenta cuentaOrigen = cuentaRepository.findById(request.getCuentaOrigenId())
                    .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta origen no encontrada"));
            validarSaldoSuficiente(cuentaOrigen, request.getMonto());
            validarLimiteDiario(cuentaOrigen, request.getMonto());
        }
    }

    private void validarSaldoSuficiente(Cuenta cuenta, BigDecimal monto) {
        if (cuenta.getSaldo().compareTo(monto) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
    }

    private void validarLimiteDiario(Cuenta cuenta, BigDecimal monto) {
        BigDecimal totalHoy = transaccionRepository.sumMontoByCuentaAndFecha(
                cuenta.getId(),
                LocalDateTime.now().toLocalDate().atStartOfDay(),
                LocalDateTime.now()
        );
        if (totalHoy.add(monto).compareTo(BigDecimal.valueOf(10000)) > 0) {
            throw new LimiteDiarioExcedidoException("Límite diario excedido ($10,000)");
        }
    }

    private Transaccion crearTransaccionPendiente(TransaccionRequest request) {
        return Transaccion.builder()
                .tipo(request.getTipo())
                .monto(request.getMonto())
                .estado(EstadoTransaccion.PENDIENTE)
                .cuentaOrigen(request.getCuentaOrigenId() != null ?
                        cuentaRepository.getReferenceById(request.getCuentaOrigenId()) : null)
                .cuentaDestino(request.getCuentaDestinoId() != null ?
                        cuentaRepository.getReferenceById(request.getCuentaDestinoId()) : null)
                .build();
    }

    private TransaccionResponse convertirAResponse(Transaccion transaccion) {
        return TransaccionResponse.builder()
                .id(transaccion.getId())
                .tipo(transaccion.getTipo())
                .monto(transaccion.getMonto())
                .estado(transaccion.getEstado())
                .fecha(transaccion.getFecha())
                .cuentaOrigenId(transaccion.getCuentaOrigen() != null ? transaccion.getCuentaOrigen().getId() : null)
                .cuentaDestinoId(transaccion.getCuentaDestino() != null ? transaccion.getCuentaDestino().getId() : null)
                .build();
    }
}