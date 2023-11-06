package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.VeiculoCondutor;
import com.code4.parquimetro.dominio.VeiculoCondutorPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoCondutorRepository
        extends JpaRepository<VeiculoCondutor, VeiculoCondutorPrimaryKey>{
}
