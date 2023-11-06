package com.code4.parquimetro.dominio;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Entity
public class ControleEstacionamento {
    @EmbeddedId
    @Setter
    @NonNull
    private ControleEstacionamentoPrimaryKey id;
    @MapsId("codigoIdentificadorEstacionamento")
    @JoinColumn(name = "codigo_identificador_estacionamento")
    @ManyToOne
    private Estacionamento estacionamento;
    @MapsId("veiculoCondutorPrimaryKey")
    @JoinColumns({
            @JoinColumn(name = "cpf_condutor"),
            @JoinColumn(name = "placa_veiculo_condutor")
    })
    @ManyToOne
    private VeiculoCondutor veiculoCondutor;
    @Setter
    @NonNull
    private TipoOpcaoTempo codigoTipoOpcaoTempo;
    @Setter
    @NonNull
    private int duracaoTempoDesejadoControleEstacionamento;
    private LocalDate timestampInicioControleEstacionamento = LocalDate.now();
    private LocalDate timestampFimControleEstacionamento;
    private BigDecimal valorTotalControleEstacionamento;
    private int numeroReciboControleEstacionamento;
}
