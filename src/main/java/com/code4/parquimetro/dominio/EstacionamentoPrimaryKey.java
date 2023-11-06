package com.code4.parquimetro.dominio;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class EstacionamentoPrimaryKey implements Serializable {
    private int codigoIdentificadorEstacionamento;
}
