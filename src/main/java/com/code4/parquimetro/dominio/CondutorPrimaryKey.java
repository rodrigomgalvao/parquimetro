package com.code4.parquimetro.dominio;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CondutorPrimaryKey implements Serializable {
    private String cpfCondutor;
}
