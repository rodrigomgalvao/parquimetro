package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.Condutor;
import com.code4.parquimetro.dominio.CondutorPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondutorRespository
        extends JpaRepository<Condutor, CondutorPrimaryKey> {
}
