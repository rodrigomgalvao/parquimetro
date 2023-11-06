package com.code4.parquimetro.controller;

import com.code4.parquimetro.controller.dto.EstacionamentoDto;
import com.code4.parquimetro.dominio.Estacionamento;
import com.code4.parquimetro.dominio.EstacionamentoPrimaryKey;
import com.code4.parquimetro.repository.EstacionamentoRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {
    @Autowired
    private Validator validator;

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarEstacionamento(@RequestBody EstacionamentoDto estacionamentoDto) {
        Map<Path, String> violacoesMap = validar(estacionamentoDto);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {

            Estacionamento estacionamento = estacionamentoDto.toEstacionamento();

            estacionamentoRepository.save(estacionamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(estacionamento);
        }
    }

    @GetMapping(value = { "/id/{id}" })
    public ResponseEntity<?> consultarEstacionamentoId(@PathVariable int id){

        Optional<Estacionamento> optionalEstacionamento = estacionamentoRepository.
                findById(new EstacionamentoPrimaryKey(id));

        if (optionalEstacionamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Estacionamento não encontrado para o ID informado.");
        } else {
            return ResponseEntity.ok(optionalEstacionamento.get());
        }

    }
// TODO: 05/11/2023 Implementar outras consultas 
    
    @GetMapping(value = { "/" })
    public ResponseEntity<Collection<Estacionamento>> findAll() {
        var estacionamentos = estacionamentoRepository.findAll();
        return ResponseEntity.ok(estacionamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirEstacionamentoId(@PathVariable int id) {

        EstacionamentoPrimaryKey estacionamentoPrimaryKey = new EstacionamentoPrimaryKey(id);

        Optional<Estacionamento> optionalEstacionamento = estacionamentoRepository.
                findById(estacionamentoPrimaryKey);

        if (optionalEstacionamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estacionamento não encontrado para exclusão.");
        } else {
            estacionamentoRepository.deleteById(estacionamentoPrimaryKey);
            return ResponseEntity.ok("Estacionamento excluído com sucesso.");
        }
// TODO: 05/11/2023 implementar verificação de estacionamento nas tabelas filhas para impedir exclusão 
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEstacionamento(
            @PathVariable int id,
            @RequestBody EstacionamentoDto estacionamentoDto) {
        Map<Path, String> violacoesMap = validar(estacionamentoDto);

        EstacionamentoPrimaryKey estacionamentoPrimaryKey = new EstacionamentoPrimaryKey(id);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {
            Optional<Estacionamento> estacionamentoExistente = estacionamentoRepository.
                    findById(estacionamentoPrimaryKey);

            if (estacionamentoExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body("Estacionamento não encontrado para atualização.");
            } else {
                Estacionamento estacionamento = estacionamentoDto.toEstacionamento();

                Estacionamento estacionamentoAtualizado = estacionamentoExistente.get();

                estacionamentoAtualizado.
                        setCnpjEstacionamento(estacionamento.getCnpjEstacionamento());
                estacionamentoAtualizado.
                        setNomeEstacionamento(estacionamento.getNomeEstacionamento());
                estacionamentoAtualizado.
                        setDdiTelefoneEstacionamento(estacionamento.getDdiTelefoneEstacionamento());
                estacionamentoAtualizado.
                        setDddTelefoneEstacionamento(estacionamento.getDddTelefoneEstacionamento());
                estacionamentoAtualizado.
                        setNumeroTelefoneEstacionamento(estacionamento.getNumeroTelefoneEstacionamento());
                estacionamentoAtualizado.
                        setEmailEstacionamento(estacionamento.getEmailEstacionamento());
                estacionamentoAtualizado.
                        setLogradouroEnderecoEstacionamento(estacionamento.getLogradouroEnderecoEstacionamento());
                estacionamentoAtualizado.
                        setBairroEnderecoEstacionamento(estacionamento.getBairroEnderecoEstacionamento());
                estacionamentoAtualizado.
                        setCidadeEnderecoEstacionamento(estacionamento.getCidadeEnderecoEstacionamento());
                estacionamentoAtualizado.
                        setSiglaUfEnderecoEstacionamento(estacionamento.getSiglaUfEnderecoEstacionamento());
                estacionamentoAtualizado.
                        setValorHoraTarifaEstacionamento(estacionamento.getValorHoraTarifaEstacionamento());

                estacionamentoRepository.save(estacionamentoAtualizado);
                return ResponseEntity.ok(estacionamentoAtualizado);
            }
        }
    }
    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesMap = violacoes.stream()
                .collect(Collectors.toMap(violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesMap;
    }
}
