package com.code4.parquimetro.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class VeiculoCondutor {
    @EmbeddedId
    private VeiculoCondutorPrimaryKey id;
    @MapsId("cpfCondutor")
    @JoinColumn(name = "cpf_condutor")
    @ManyToOne
    private Condutor condutor;
    private String marcaVeiculoCondutor;
    private String modeloVeiculoCondutor;
    private TipoCorVeiculo corVeiculoCondutor;
    private String anoFabricacaoVeiculoCondutor;
    private String anoModeloVeiculoCondutor;

}
