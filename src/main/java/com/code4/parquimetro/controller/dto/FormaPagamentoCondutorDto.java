package com.code4.parquimetro.controller.dto;

import com.code4.parquimetro.dominio.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;

public class FormaPagamentoCondutorDto {
    @JsonProperty
    @NotNull(message = "Campo CPF do condutor não pode ser nulo.")
    private Condutor condutor;
    @JsonProperty
    @Min(value = 0, message = "Campo tipo da forma de pagamento do condutor deve ser entre 0 e 2.")
    @Max(value = 2, message = "Campo tipo da forma de pagamento do condutor deve ser entre 0 e 2.")
    @NotNull(message = "Campo tipo da forma de pagamento do condutor não pode ser nulo.")
    private int codigoTipoFormaPagamento;
    @JsonProperty
    @NotBlank(message = "Campo número do cartão da forma de pagamento do condutor não pode ser branco ou nulo.")
    @CreditCardNumber(message = "Número do cartão inválido")
    private String numeroCartaoFormaPagamento;
    @JsonProperty
    @NotBlank(message = "Campo nome do titular do cartão da forma de pagamento do condutor não pode ser branco ou nulo.")
    private String nomeTitularCartaoFormaPagamento;
    @JsonProperty
    @NotNull(message = "Campo mês da validade do cartão da forma de pagamento do condutor não pode ser nulo.")
    private int mesValidadeCartaoFormaPagamento;
    @JsonProperty
    @NotNull(message = "Campo ano da validade do cartão da forma de pagamento do condutor não pode ser nulo.")
    private int anoValidadeCartaoFormaPagamento;
    @JsonProperty
    @NotNull(message = "Campo valor verificador do cartão da forma de pagamento do condutor não pode ser nulo.")
    private int valorVerificadorCartaoFormaPagamento;
    @JsonProperty
    @NotBlank(message = "Campo indicador da forma de pagamento preferida do condutor não pode ser branco ou nulo.")
    private String indicadorFormaPagamentoPreferida;

    public FormaPagamentoCondutor toFormaDePagamentoCondutor(){
        return new FormaPagamentoCondutor(
                new FormaPagamentoCondutorPrimaryKey(condutor.getCpfCondutor(), 0),
                condutor,
                TipoFormaPagamento.values()[codigoTipoFormaPagamento],
                numeroCartaoFormaPagamento,
                nomeTitularCartaoFormaPagamento,
                mesValidadeCartaoFormaPagamento,
                anoValidadeCartaoFormaPagamento,
                valorVerificadorCartaoFormaPagamento,
                indicadorFormaPagamentoPreferida);
    }
}
