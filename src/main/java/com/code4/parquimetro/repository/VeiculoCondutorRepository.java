package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.VeiculoCondutor;
import com.code4.parquimetro.dominio.VeiculoCondutorPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoCondutorRepository
        extends JpaRepository<VeiculoCondutor, VeiculoCondutorPrimaryKey>{
    @Query(value = "select * from veiculo_condutor where cpf_condutor = :cpf_condutor ", nativeQuery = true)
    List<VeiculoCondutor> findVeiculoCondutorByCpfCondutor(@Param("cpf_condutor") String cpfCondutor);

    @Query(value = "select * from veiculo_condutor where placa_veiculo_condutor = :placa_veiculo_condutor ",
            nativeQuery = true)
    List<VeiculoCondutor> findVeiculoCondutorByPlaca(@Param("placa_veiculo_condutor") String placaVeiculoCondutor);
}
