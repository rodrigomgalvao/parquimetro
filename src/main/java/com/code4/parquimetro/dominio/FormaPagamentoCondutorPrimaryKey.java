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
public class FormaPagamentoCondutorPrimaryKey implements Serializable {
    private CondutorPrimaryKey cpfCondutor;
    private int numeroSequencialFormaPagamento;
}
