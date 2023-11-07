package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.FormaPagamentoCondutor;
import com.code4.parquimetro.dominio.FormaPagamentoCondutorPrimaryKey;
import com.code4.parquimetro.dominio.VeiculoCondutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormaPagamentoCondutorRepository
        extends JpaRepository<FormaPagamentoCondutor, FormaPagamentoCondutorPrimaryKey> {
    @Query(value = "select ifnull(max(numero_sequencial_forma_pagamento), 0)" +
            "from forma_pagamento_condutor where cpf_condutor = :cpf_condutor", nativeQuery = true)
    int getMaxNumeroSequencialCondutor(@Param("cpf_condutor") String cpfCondutor);

    @Query(value = "select * from forma_pagamento_condutor where cpf_condutor = :cpf_condutor ", nativeQuery = true)
    List<FormaPagamentoCondutor> findFormaPagamentoCondutorByCpfCondutor(@Param("cpf_condutor") String cpfCondutor);

}
