package com.transactions.bank.application.port.out;

public interface EventPublisherPort {
    void publicarTransaccionPendiente(Long transaccionId);
}
