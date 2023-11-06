package com.code4.parquimetro.dominio;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Entity
public class Estacionamento {
    @EmbeddedId
    private EstacionamentoPrimaryKey codigoIdentificadorEstacionamento;
    @Setter
    @NonNull
    private String cnpjEstacionamento;
    @Setter
    @NonNull
    private String nomeEstacionamento;
    @Setter
    @NonNull
    private TipoDiscagemDiretaInternacional ddiTelefoneEstacionamento;
    @Setter
    @NonNull
    private TipoDiscagemDiretaDistancia dddTelefoneEstacionamento;
    @Setter
    @NonNull
    private String numeroTelefoneEstacionamento;
    @Setter
    @NonNull
    private String emailEstacionamento;
    @Setter
    @NonNull
    private String logradouroEnderecoEstacionamento;
    @Setter
    @NonNull
    private String bairroEnderecoEstacionamento;
    @Setter
    @NonNull
    private String cidadeEnderecoEstacionamento;
    @Setter
    @NonNull
    private TipoUnidadeFederativa siglaUfEnderecoEstacionamento;
    @Setter
    @NonNull
    private BigDecimal valorHoraTarifaEstacionamento;

    public void setCodigoIdentificadorEstacionamento(EstacionamentoPrimaryKey estacionamentoPrimaryKey){
        codigoIdentificadorEstacionamento = estacionamentoPrimaryKey;
    }
}
