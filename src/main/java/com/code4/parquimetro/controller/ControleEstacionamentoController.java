package com.code4.parquimetro.controller;

import com.code4.parquimetro.controller.dto.ControleEstacionamentoDto;
import com.code4.parquimetro.dominio.*;
import com.code4.parquimetro.repository.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/controlesestacionamento")
public class ControleEstacionamentoController {
    @Autowired
    private Validator validator;

    @Autowired
    private ControleEstacionamentoRepository controleEstacionamentoRepository;

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private VeiculoCondutorRepository veiculoCondutorRepository;

    @Autowired
    private FormaPagamentoCondutorRepository formaPagamentoCondutorRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarControleEstacionamento(
            @RequestBody ControleEstacionamentoDto controleEstacionamentoDto) {
        Map<Path, String> violacoesMap = validar(controleEstacionamentoDto);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {

            ControleEstacionamento controleEstacionamento = controleEstacionamentoDto.toControleEstacionamento();

            Optional<Estacionamento> optionalEstacionamento =
                    estacionamentoRepository.findById(
                            controleEstacionamento.getEstacionamento().getCodigoIdentificadorEstacionamento());

            if (optionalEstacionamento.isEmpty()){
                return ResponseEntity.badRequest().
                        body("Cadastramento não permitido: ID do estacionamento não encontrado.");
            }

            Optional<VeiculoCondutor> optionalVeiculoCondutor =
                    veiculoCondutorRepository.findById(
                            controleEstacionamento.getVeiculoCondutor().getId());

            if (optionalVeiculoCondutor.isEmpty()){
                return ResponseEntity.badRequest().
                        body("Cadastramento não permitido: veículo não encontrado para o CPF e placa informados.");
            }


            controleEstacionamento.setEstacionamento(optionalEstacionamento.get());
            controleEstacionamento.setVeiculoCondutor(optionalVeiculoCondutor.get());

            int idEstacionamento =
                    controleEstacionamento.getEstacionamento().
                            getCodigoIdentificadorEstacionamento().
                            getCodigoIdentificadorEstacionamento();

            String cpfCondutor =
                    controleEstacionamento.getVeiculoCondutor().
                            getId().
                            getCpfCondutor().
                            getCpfCondutor();

            String placaVeiculo =
                    controleEstacionamento.getVeiculoCondutor().
                            getId().
                            getPlacaVeiculoCondutor();

            int codigoTipoFormaPagamentoPreferida =
                    formaPagamentoCondutorRepository.getTipoFormaPagamentoPreferidaCondutor(cpfCondutor);

            // Se forma de pagamento preferida for PIX, não permite iniciar controle de estacionamento
            // para opção de tempo variável
            if (codigoTipoFormaPagamentoPreferida == TipoFormaPagamento.PIX.ordinal() &&
                    controleEstacionamento.getCodigoTipoOpcaoTempo().ordinal() == TipoOpcaoTempo.VARIAVEL.ordinal()){
                return ResponseEntity.badRequest().
                        body("Cadastramento não permitido: condutor com forma de pagamento PIX somente pode " +
                                "utilizar opção fixa de tempo.");
            }

            controleEstacionamento.setId(
                    new ControleEstacionamentoPrimaryKey(
                            controleEstacionamento.getEstacionamento().getCodigoIdentificadorEstacionamento(),
                            controleEstacionamento.getVeiculoCondutor().getId(),
                            controleEstacionamentoRepository.
                                    getMaxNumeroSequencialControleEstacionamento(
                                    idEstacionamento, cpfCondutor, placaVeiculo)+1));

            controleEstacionamentoRepository.save(controleEstacionamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(controleEstacionamento);
        }
    }

    // TODO: 07/11/2023 Implementar consultas diversas
    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesMap = violacoes.stream()
                .collect(Collectors.toMap(violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesMap;
    }
}
