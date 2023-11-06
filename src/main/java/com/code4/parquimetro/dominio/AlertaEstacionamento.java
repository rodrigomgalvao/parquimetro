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
public class AlertaEstacionamento {
    @EmbeddedId
    private AlertaEstacionamentoPrimaryKey id;
    @MapsId("controleEstacionamentoPrimaryKey")
    @JoinColumns({
            @JoinColumn(name = "codigo_identificador_estacionamento"),
            @JoinColumn(name = "cpf_condutor"),
            @JoinColumn(name = "placa_veiculo_condutor"),
            @JoinColumn(name = "numero_sequencial_controle_estacionamento")
    })
    @ManyToOne
    private ControleEstacionamento controleEstacionamento;
    @ManyToOne
    @JoinColumn(name = "codigo_mensagem_alerta")
    private MensagemAlerta mensagemAlerta;
}
