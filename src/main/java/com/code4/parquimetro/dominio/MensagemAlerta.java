package com.code4.parquimetro.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MensagemAlerta {
    @Id
    private int codigoMensagemAlerta;
    private String textoMensagemAlerta;
}
