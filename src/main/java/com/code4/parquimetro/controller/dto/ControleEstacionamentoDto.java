package com.code4.parquimetro.controller.dto;

import com.code4.parquimetro.dominio.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ControleEstacionamentoDto {
    @JsonProperty
    @NotNull(message = "Campo ID do estacionamento não pode ser nulo.")
    private Estacionamento estacionamento;
    @JsonProperty
    @NotNull(message = "Campo CPF e placa do veículo não podem ser nulos.")
    private VeiculoCondutor veiculoCondutor;
    @JsonProperty
    @Min(value = 0, message = "Campo tipo de opção de tempo do controle de estacionamento deve ser 0 ou 1.")
    @Max(value = 1, message = "Campo tipo de opção de tempo do controle de estacionamento deve ser 0 ou 1.")
    @NotNull(message = "Campo tipo de opção de tempo do controle de estacionamento não pode ser nulo.")
    private int codigoTipoOpcaoTempo;
    @JsonProperty
    @NotNull(message = "Campo duração do tempo desejado do condutor não pode ser nulo.")
    private int duracaoTempoDesejadoControleEstacionamento;

    public ControleEstacionamento toControleEstacionamento(){
        return new ControleEstacionamento(
                new ControleEstacionamentoPrimaryKey(
                        estacionamento.getCodigoIdentificadorEstacionamento(),
                        veiculoCondutor.getId(), 0),
                        estacionamento,
                        veiculoCondutor,
                        TipoOpcaoTempo.values()[codigoTipoOpcaoTempo],
                        duracaoTempoDesejadoControleEstacionamento);
    }
}
