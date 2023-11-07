package com.code4.parquimetro.controller.dto;

import com.code4.parquimetro.dominio.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VeiculoCondutorDto {
    @JsonProperty
    @NotNull(message = "Campo CPF do condutor não pode ser nulo.")
    private Condutor condutor;
    @JsonProperty
    @NotBlank(message = "Campo placa do veículo do condutor não pode ser branco ou nulo.")
    private String placaVeiculoCondutor;
    @JsonProperty
    @NotBlank(message = "Campo placa do veículo do condutor não pode ser branco ou nulo.")
    private String marcaVeiculoCondutor;
    @JsonProperty
    @NotBlank(message = "Campo placa do veículo do condutor não pode ser branco ou nulo.")
    private String modeloVeiculoCondutor;
    @JsonProperty
    @Min(value = 0, message = "Campo cor do veículo deve ser entre 0 e 11.")
    @Max(value = 11, message = "Campo cor do veículo deve ser entre 0 e 11.")
    @NotNull(message = "Campo cor do veículo do condutor não pode ou nulo.")
    private int corVeiculoCondutor;
    @JsonProperty
    @NotBlank(message = "Campo ano fabricação do veículo do condutor não pode ser branco ou nulo.")
    private String anoFabricacaoVeiculoCondutor;
    @JsonProperty
    @NotBlank(message = "Campo ano modelo do veículo do condutor não pode ser branco ou nulo.")
    private String anoModeloVeiculoCondutor;
    public VeiculoCondutor toVeiculoCondutor(){
        return new VeiculoCondutor(
                new VeiculoCondutorPrimaryKey(condutor.getCpfCondutor(), placaVeiculoCondutor),
                condutor,
                marcaVeiculoCondutor,
                modeloVeiculoCondutor,
                TipoCorVeiculo.values()[corVeiculoCondutor],
                anoFabricacaoVeiculoCondutor,
                anoModeloVeiculoCondutor);
    }
}
