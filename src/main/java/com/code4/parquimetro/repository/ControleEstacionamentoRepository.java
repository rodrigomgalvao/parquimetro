package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.ControleEstacionamento;
import com.code4.parquimetro.dominio.ControleEstacionamentoPrimaryKey;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ControleEstacionamentoRepository
        extends JpaRepository<ControleEstacionamento, ControleEstacionamentoPrimaryKey> {
    @Query(value =
            "select ifnull(max(numero_sequencial_controle_estacionamento), 0)" +
            "from controle_estacionamento " +
            "where codigo_identificador_estacionamento = :codigo_identificador_estacionamento" +
            "  and cpf_condutor = :cpf_condutor" +
            "  and placa_veiculo_condutor = :placa_veiculo_condutor", nativeQuery = true)
    int getMaxNumeroSequencialControleEstacionamento(
            @Param("codigo_identificador_estacionamento") int codigoIdentificadorEstacionamento,
            @Param("cpf_condutor") String cpfCondutor,
            @Param("placa_veiculo_condutor") String placaVeiculoCondutor);
    

    
    @Query("SELECT ce FROM ControleEstacionamento ce WHERE ce.timestampFimControleEstacionamento IS NULL " +
            "AND ce.timestampInicioControleEstacionamento <= :targetTime " +
            "AND TIMESTAMPADD(SECOND, 3600, ce.timestampInicioControleEstacionamento) >= :targetTime")
    List<ControleEstacionamento> findEstacionamentosPrestesAExpirar(@Param("targetTime") LocalDateTime targetTime);


    
    
    
    
    
}
