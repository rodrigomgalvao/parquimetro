package com.code4.parquimetro.controller.dto;

import com.code4.parquimetro.dominio.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public class CondutorDto {
    @JsonProperty
    @NotBlank(message = "Campo CPF do condutor não pode ser branco ou nulo.")
    @CPF
    private String cpfCondutor;
    @JsonProperty
    @NotBlank(message = "Campo nome do condutor não pode ser branco ou nulo.")
    private String nomeCondutor;
    @JsonProperty
    @Min(value = 0, message = "Campo DDI deve ser entre 0 e 999.")
    @Max(value = 999, message = "Campo DDI deve ser entre 0 e 999.")
    @NotNull(message = "Campo DDI não pode ser nulo.")
    private int ddiTelefoneCondutor;
    @JsonProperty
    @Min(value = 0, message = "Campo DDD deve ser entre 0 e 99.")
    @Max(value = 99, message = "Campo DDD deve ser entre 0 e 99.")
    @NotNull(message = "Campo DDD não pode ser nulo.")
    private int dddTelefoneCondutor;
    @JsonProperty
    @NotBlank(message = "Campo número de telefone do condutor não pode ser branco ou nulo.")
    private String numeroTelefoneCondutor;
    @JsonProperty
    @NotBlank(message = "Campo e-mail do condutor não pode ser branco ou nulo.")
    @Email(message = "E-mail inválido.")
    private String emailCondutor;
    @JsonProperty
    @NotBlank(message = "Campo logradouro do endereço do condutor não pode ser branco ou nulo.")
    private String logradouroEnderecoCondutor;
    @JsonProperty
    @NotBlank(message = "Campo bairro do endereço do condutor não pode ser branco ou nulo.")
    private String bairroEnderecoCondutor;
    @JsonProperty
    @NotBlank(message = "Campo cidade do endereço do condutor não pode ser branco ou nulo.")
    private String cidadeEnderecoCondutor;
    @JsonProperty
    @NotBlank(message = "Campo sigla da UF do endereço do condutor não pode ser branco ou nulo.")
    private String siglaUfEnderecoCondutor;
    public Condutor toCondutor(){
        return new Condutor(new CondutorPrimaryKey(cpfCondutor),
                nomeCondutor,
                TipoDiscagemDiretaInternacional.values()[ddiTelefoneCondutor],
                TipoDiscagemDiretaDistancia.values()[dddTelefoneCondutor],
                numeroTelefoneCondutor,
                emailCondutor,
                logradouroEnderecoCondutor,
                bairroEnderecoCondutor,
                cidadeEnderecoCondutor,
                TipoUnidadeFederativa.fromSigla(siglaUfEnderecoCondutor));
    }
}
