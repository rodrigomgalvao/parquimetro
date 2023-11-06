package com.code4.parquimetro.dominio;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
public class VeiculoCondutorPrimaryKey implements Serializable {
    private CondutorPrimaryKey cpfCondutor;
    private String placaVeiculoCondutor;
}
