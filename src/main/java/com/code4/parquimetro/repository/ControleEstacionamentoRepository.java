package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.ControleEstacionamento;
import com.code4.parquimetro.dominio.ControleEstacionamentoPrimaryKey;
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
}
