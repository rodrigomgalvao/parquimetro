package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.AlertaEstacionamento;
import com.code4.parquimetro.dominio.AlertaEstacionamentoPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaEstacionamentoRepository
        extends JpaRepository<AlertaEstacionamento, AlertaEstacionamentoPrimaryKey> {
}
