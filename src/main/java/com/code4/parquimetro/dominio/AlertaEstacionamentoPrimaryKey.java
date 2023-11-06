package com.code4.parquimetro.dominio;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@EqualsAndHashCode
public class AlertaEstacionamentoPrimaryKey implements Serializable {
    private ControleEstacionamentoPrimaryKey controleEstacionamentoPrimaryKey;
    private LocalDate timestampAlertaEstacionamento;
}
