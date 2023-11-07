package com.code4.parquimetro.controller.dto;

import com.code4.parquimetro.dominio.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;

public class EstacionamentoDto {
    @JsonProperty
    @NotBlank(message = "Campo CNPJ do estacionamento não pode ser branco ou nulo.")
    @CNPJ
    private String cnpjEstacionamento;
    @JsonProperty
    @NotBlank(message = "Campo nome do estacionamento não pode ser branco ou nulo.")
    private String nomeEstacionamento;
    @JsonProperty
    @Min(value = 0, message = "Campo DDI deve ser entre 0 e 999.")
    @Max(value = 999, message = "Campo DDI deve ser entre 0 e 999.")
    @NotNull(message = "Campo DDI não pode ser nulo.")
    private int ddiTelefoneEstacionamento;
    @JsonProperty
    @Min(value = 0, message = "Campo DDD deve ser entre 0 e 99.")
    @Max(value = 99, message = "Campo DDD deve ser entre 0 e 99.")
    @NotNull(message = "Campo DDD não pode ser nulo.")
    private int dddTelefoneEstacionamento;
    @JsonProperty
    @NotBlank(message = "Campo número de telefone do estacionamento não pode ser branco ou nulo.")
    private String numeroTelefoneEstacionamento;
    @JsonProperty
    @NotBlank(message = "Campo e-mail do estacionamento não pode ser branco ou nulo.")
    @Email(message = "E-mail inválido")
    private String emailEstacionamento;
    @JsonProperty
    @NotBlank(message = "Campo logradouro do endereço do estacionamento não pode ser branco ou nulo.")
    private String logradouroEnderecoEstacionamento;
    @JsonProperty
    @NotBlank(message = "Campo bairro do endereço do estacionamento não pode ser branco ou nulo.")
    private String bairroEnderecoEstacionamento;
    @JsonProperty
    @NotBlank(message = "Campo cidade do endereço do estacionamento não pode ser branco ou nulo.")
    private String cidadeEnderecoEstacionamento;
    @JsonProperty
    @NotBlank(message = "Campo sigla da UF do endereço do estacionamento não pode ser branco ou nulo.")
    private String siglaUfEnderecoEstacionamento;
    @JsonProperty
    @NotNull(message = "Campo valor da tarida do estacionamento não pode ser nulo.")
    private BigDecimal valorHoraTarifaEstacionamento;

    public Estacionamento toEstacionamento(){
        return new Estacionamento(cnpjEstacionamento,
                nomeEstacionamento,
                TipoDiscagemDiretaInternacional.values()[ddiTelefoneEstacionamento],
                TipoDiscagemDiretaDistancia.values()[dddTelefoneEstacionamento],
                numeroTelefoneEstacionamento,
                emailEstacionamento,
                logradouroEnderecoEstacionamento,
                bairroEnderecoEstacionamento,
                cidadeEnderecoEstacionamento,
                TipoUnidadeFederativa.fromSigla(siglaUfEnderecoEstacionamento),
                valorHoraTarifaEstacionamento);
    }
}
