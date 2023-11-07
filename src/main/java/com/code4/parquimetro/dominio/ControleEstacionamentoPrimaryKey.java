package com.code4.parquimetro.dominio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ControleEstacionamentoPrimaryKey implements Serializable {
    private EstacionamentoPrimaryKey codigoIdentificadorEstacionamento;
    private VeiculoCondutorPrimaryKey veiculoCondutorPrimaryKey;
    private int numeroSequencialControleEstacionamento;
}
