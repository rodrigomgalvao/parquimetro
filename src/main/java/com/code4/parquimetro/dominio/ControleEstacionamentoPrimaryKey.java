package com.code4.parquimetro.dominio;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
public class ControleEstacionamentoPrimaryKey implements Serializable {
    private EstacionamentoPrimaryKey codigoIdentificadorEstacionamento;
    private VeiculoCondutorPrimaryKey veiculoCondutorPrimaryKey;
    private int numeroSequencialControleEstacionamento;
}
