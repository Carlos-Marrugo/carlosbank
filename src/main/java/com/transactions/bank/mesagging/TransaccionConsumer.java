package com.transactions.bank.mesagging;

import com.transactions.bank.adapters.persistence.CuentaRepository;
import com.transactions.bank.adapters.persistence.TransaccionRepository;
import com.transactions.bank.config.RabbitMQConfig;
import com.transactions.bank.domain.Cuenta;
import com.transactions.bank.domain.EstadoTransaccion;
import com.transactions.bank.domain.Transaccion;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

@Component
public class TransaccionConsumer {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    @Transactional
    public void procesarTransacciones(Long transaccionId) {
        Transaccion transaccion = transaccionRepository.findById(transaccionId)
                .orElseThrow(() -> new RuntimeException("Transaccion no encontrada"));

        try {
            ejecutarTransaccion(transaccion);
            transaccion.setEstado(EstadoTransaccion.COMPLETADA);
        } catch (Exception e) {
            transaccion.setEstado(EstadoTransaccion.RECHAZADA);
        }
        transaccionRepository.save(transaccion);
    }

    private void ejecutarTransaccion(Transaccion transaccion) {
        switch (transaccion.getTipo()) {
            case DEPOSITO:
                Cuenta cuentaDestino = transaccion.getCuentaDestino();
                cuentaDestino.actualizarSaldo(transaccion.getMonto(), true);
                cuentaRepository.save(cuentaDestino);
                break;
            case RETIRO:
                Cuenta cuentaOrigen = transaccion.getCuentaOrigen();
                cuentaOrigen.actualizarSaldo(transaccion.getMonto(), false);
                cuentaRepository.save(cuentaOrigen);
                break;
            case TRANSFERENCIA:
                Cuenta origen = transaccion.getCuentaOrigen();
                Cuenta destino = transaccion.getCuentaDestino();
                origen.actualizarSaldo(transaccion.getMonto(), false);
                destino.actualizarSaldo(transaccion.getMonto(), true);
                cuentaRepository.save(origen);
                cuentaRepository.save(destino);
                break;
        }
    }
}