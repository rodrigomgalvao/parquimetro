package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.ControleEstacionamento;
import com.code4.parquimetro.dominio.ControleEstacionamentoPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControleEstacionamentoRepository
        extends JpaRepository<ControleEstacionamento, ControleEstacionamentoPrimaryKey> {
}
