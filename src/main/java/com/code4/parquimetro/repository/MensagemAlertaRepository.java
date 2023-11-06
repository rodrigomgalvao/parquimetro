package com.code4.parquimetro.repository;

import com.code4.parquimetro.dominio.MensagemAlerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemAlertaRepository
        extends JpaRepository<MensagemAlerta, Integer> {
}
