package com.code4.parquimetro.dominio;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VeiculoCondutorPrimaryKey implements Serializable {
    private CondutorPrimaryKey cpfCondutor;
    private String placaVeiculoCondutor;
}
