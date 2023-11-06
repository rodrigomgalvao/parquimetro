package com.code4.parquimetro.ServiceImpl;

import java.time.LocalDateTime;

public interface VerificaHoraService {
    void iniciarVerificacao(LocalDateTime timestampInicio);
    void verificarEstacionamento();
}