package com.transactions.bank.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transactions.bank.domain.model.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    @Query("SELECT t FROM Transaccion t WHERE t.cuentaOrigen.id = :cuentaId OR t.cuentaDestino.id = :cuentaId")
    List<Transaccion> findTransactionsByAccountId(@Param("cuentaId") Long cuentaId);

    @Query("SELECT COALESCE(SUM(t.monto), 0) FROM Transaccion t " +
            "WHERE t.cuentaOrigen.id = :cuentaId " +
            "AND t.fecha BETWEEN :inicio AND :fin " +
            "AND t.estado = 'COMPLETADA'")
    BigDecimal sumMontoByCuentaAndFecha(
            @Param("cuentaId") Long cuentaId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}