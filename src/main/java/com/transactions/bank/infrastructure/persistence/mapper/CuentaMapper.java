package com.transactions.bank.infrastructure.persistence.mapper;

import com.transactions.bank.domain.model.Cuenta;
import com.transactions.bank.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {
    
    public Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) return null;
        
        return new Cuenta(
            entity.getId(),
            entity.getNumeroCuenta(),
            entity.getPropietario(),
            entity.getSaldo(),
            entity.getFechaCreacion()
        );
    }
    
    public CuentaEntity toEntity(Cuenta domain) {
        if (domain == null) return null;
        
        CuentaEntity entity = new CuentaEntity();
        entity.setId(domain.getId());
        entity.setNumeroCuenta(domain.getNumeroCuenta());
        entity.setPropietario(domain.getPropietario());
        entity.setSaldo(domain.getSaldo());
        entity.setFechaCreacion(domain.getFechaCreacion());
        return entity;
    }
}