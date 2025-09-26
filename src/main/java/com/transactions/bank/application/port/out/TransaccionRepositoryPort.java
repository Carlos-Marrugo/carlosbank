package com.transactions.bank.application.port.out;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.transactions.bank.domain.model.Transaccion;

public interface TransaccionRepositoryPort {

    Transaccion save(Transaccion transaccion);
    Optional<Transaccion> findById(Long id);
    List<Transaccion> findAll();
    List<Transaccion> findTransactionsByAccountId(Long cuentaId);
    BigDecimal sumMontoByCuentaAndFecha(Long cuentaId, LocalDateTime inicio, LocalDateTime fin);

    
}