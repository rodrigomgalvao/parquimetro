package com.code4.parquimetro.jobs;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.code4.parquimetro.dominio.ControleEstacionamento;
import com.code4.parquimetro.repository.ControleEstacionamentoRepository;

@Component
public class VerificacaoEstacionamentoJob {
    private final ControleEstacionamentoRepository controleEstacionamentoRepository;

    public VerificacaoEstacionamentoJob(ControleEstacionamentoRepository controleEstacionamentoRepository) {
        this.controleEstacionamentoRepository = controleEstacionamentoRepository;
    }

    @Scheduled(fixedDelay = 60000) // Executar a cada minuto
    public void verificarEstacionamento() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Verificando estacionamento...");

        // Obtenha os registros de controle de estacionamento que estão prestes a expirar
        List<ControleEstacionamento> estacionamentosPrestesAExpirar = controleEstacionamentoRepository
                .findEstacionamentosPrestesAExpirar(now);

        for (ControleEstacionamento controleEstacionamento : estacionamentosPrestesAExpirar) {
            LocalDateTime horaExpiracaoPrevista = controleEstacionamento.getTimestampInicioControleEstacionamento()
                    .plusHours(1);  //add 1hora ao tempo de início para obter a hora de expiração.

            // Verificamos se faltam 5 minutos para a hora prevista de expiração
            if (now.plusMinutes(5).isAfter(horaExpiracaoPrevista)) {
                enviarAlerta(controleEstacionamento);
            }
        }
    }

    private void enviarAlerta(ControleEstacionamento controleEstacionamento) {
        System.out.println("ALERTA: Faltam 5 minutos para encerrar o período de 1 hora.");
        // Imprimir informações adicionais, se necessário.
        System.out.println("Veículo: " + controleEstacionamento.getVeiculoCondutor().getModeloVeiculoCondutor());
    }
}



//package com.code4.parquimetro.jobs;
//
//
//
//
//import com.code4.parquimetro.dominio.ControleEstacionamento;
//import com.code4.parquimetro.repository.ControleEstacionamentoRepository;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component
//public class VerificacaoEstacionamentoJob {
//    private final ControleEstacionamentoRepository controleEstacionamentoRepository;
//
//    public VerificacaoEstacionamentoJob(ControleEstacionamentoRepository controleEstacionamentoRepository) {
//        this.controleEstacionamentoRepository = controleEstacionamentoRepository;
//    }
//
//    @Scheduled(fixedDelay = 60000) // Executar a cada minuto
//    public void verificarEstacionamento() {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println("Verificando estacionamento...");
//
//        // Obtenha os registros de controle de estacionamento que estão prestes a fechar
//        List<ControleEstacionamento> estacionamentosPrestesAFecharem = controleEstacionamentoRepository
//                .findEstacionamentosPrestesAFecharem(now);
//
//        for (ControleEstacionamento controleEstacionamento : estacionamentosPrestesAFecharem) {
//            LocalDateTime horaFechamentoPrevista = controleEstacionamento.getTimestampInicioControleEstacionamento()
//                    .plusHours(1); // Adicione 1 hora ao tempo de início para fazermos o parametro.
//
//            // Verificamos se faltam 5 minutos para a hora prevista de fechamento
//            if (now.plusMinutes(5).isAfter(horaFechamentoPrevista)) {
//                
//                enviarAlerta(controleEstacionamento);
//            }
//        }
//    }
//
//    private void enviarAlerta(ControleEstacionamento controleEstacionamento) {
//        System.out.println("ALERTA: Faltam  5 minutos para encerarr o periodo de 1 hora.");
//        // imprimir informações adicionais, se necessário.
//        System.out.println("Veículo: " + controleEstacionamento.getVeiculoCondutor().getModeloVeiculoCondutor());
//    }
//}
