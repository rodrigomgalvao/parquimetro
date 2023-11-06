package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.FormaPagamentoCondutor;
import com.code4.parquimetro.dominio.FormaPagamentoCondutorPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoCondutorRepository
        extends JpaRepository<FormaPagamentoCondutor, FormaPagamentoCondutorPrimaryKey> {
}
