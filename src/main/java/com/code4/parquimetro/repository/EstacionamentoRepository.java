package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.Estacionamento;
import com.code4.parquimetro.dominio.EstacionamentoPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstacionamentoRepository
        extends JpaRepository<Estacionamento, EstacionamentoPrimaryKey> {
    @Query(value = "select ifnull(max(codigo_identificador_estacionamento), 0)" +
            "from estacionamento", nativeQuery = true)
    int getMaxEstacionamentoId();
}
