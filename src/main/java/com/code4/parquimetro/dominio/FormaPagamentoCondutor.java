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
public class FormaPagamentoCondutor {
    @EmbeddedId
    private FormaPagamentoCondutorPrimaryKey id;
    @MapsId("cpfCondutor")
    @JoinColumn(name = "cpf_condutor")
    @ManyToOne
    private Condutor condutor;
    private TipoFormaPagamento codigoTipoFormaPagamento;
    private String numeroCartaoFormaPagamento;
    private String nomeTitularCartaoFormaPagamento;
    private int mesValidadeCartaoFormaPagamento;
    private int anoValidadeCartaoFormaPagamento;
    private int valorVerificadorCartaoFormaPagamento;
    private String indicadorFormaPagamentoPreferida;
}
