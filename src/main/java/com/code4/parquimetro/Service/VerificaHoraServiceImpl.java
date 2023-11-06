package com.code4.parquimetro.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.code4.parquimetro.ServiceImpl.VerificaHoraService;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class VerificaHoraServiceImpl implements VerificaHoraService {
    private LocalDateTime timestampInicioControleEstacionamento;
    private boolean alertaEnviado;

    @Override
    public void iniciarVerificacao(LocalDateTime timestampInicio) {
        this.timestampInicioControleEstacionamento = timestampInicio;
        this.alertaEnviado = false;
    }

    @Scheduled(fixedRate = 60000) //aqui vamos  Executar a cada minuto
    public void verificarEstacionamento() {
        if (timestampInicioControleEstacionamento == null) {
            
            return;
        }

        LocalDateTime agora = LocalDateTime.now();
        Duration duracaoEstacionamento = Duration.between(timestampInicioControleEstacionamento, agora);
        long minutosEstacionamento = duracaoEstacionamento.toMinutes();

        if (minutosEstacionamento >= 60) {
            long horasEstacionamento = minutosEstacionamento / 60;
            long minutosRestantes = minutosEstacionamento % 60;

            if (minutosRestantes >= 55 && !alertaEnviado) {
                // Aqui vamos Emitir um aviso faltando 5 minutos para cada hora estacionada
                System.out.println("Aviso: Faltam 5 minutos para cada hora estacionada.");
                alertaEnviado = true;
            } else if (minutosRestantes < 55) {
                alertaEnviado = false;
            }
        }
    }
}
