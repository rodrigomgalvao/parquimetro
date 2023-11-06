package com.code4.parquimetro.dominio;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Condutor {
    @EmbeddedId
    private CondutorPrimaryKey cpfCondutor;
    private String nomeCondutor;
    private TipoDiscagemDiretaInternacional ddiTelefoneCondutor;
    private TipoDiscagemDiretaDistancia dddTelefoneCondutor;
    private String numeroTelefoneCondutor;
    private String emailCondutor;
    private String logradouroEnderecoCondutor;
    private String bairroEnderecoCondutor;
    private String cidadeEnderecoCondutor;
    private TipoUnidadeFederativa siglaUfEnderecoCondutor;
}
