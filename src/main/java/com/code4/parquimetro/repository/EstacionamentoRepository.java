package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.Estacionamento;
import com.code4.parquimetro.dominio.EstacionamentoPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoRepository
        extends JpaRepository<Estacionamento, EstacionamentoPrimaryKey> {
}
