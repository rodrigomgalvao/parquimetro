package com.code4.parquimetro.controller;

import com.code4.parquimetro.controller.dto.FormaPagamentoCondutorDto;
import com.code4.parquimetro.dominio.*;
import com.code4.parquimetro.repository.CondutorRespository;
import com.code4.parquimetro.repository.FormaPagamentoCondutorRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formaspagamento")
public class FormaPagamentoCondutorController {
    @Autowired
    private Validator validator;

    @Autowired
    private FormaPagamentoCondutorRepository formaPagamentoCondutorRepository;

    @Autowired
    private CondutorRespository condutorRespository;

    @PostMapping
    public ResponseEntity<?> cadastrarFormaPagamentoCondutor(
            @RequestBody FormaPagamentoCondutorDto formaPagamentoCondutorDto) {
        Map<Path, String> violacoesMap = validar(formaPagamentoCondutorDto);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {

            FormaPagamentoCondutor formaPagamentoCondutor = formaPagamentoCondutorDto.toFormaDePagamentoCondutor();

            Optional<Condutor> optionalCondutor =
                    condutorRespository.findById(formaPagamentoCondutor.getCondutor().getCpfCondutor());

            if (optionalCondutor.isEmpty()){
                return ResponseEntity.badRequest().
                        body("Cadastramento não permitido: CPF do condutor do veículo inexistente na base.");
            }
// TODO: 07/11/2023 Implementar crítica para tentativa de cadastramento de forma de pagamento preferida se já houver 
            formaPagamentoCondutor.setCondutor(optionalCondutor.get());

            String cpfCondutor = formaPagamentoCondutor.getId().getCpfCondutor().getCpfCondutor();
            formaPagamentoCondutor.setId(
                    new FormaPagamentoCondutorPrimaryKey(formaPagamentoCondutor.getId().getCpfCondutor(),
                            formaPagamentoCondutorRepository.
                                    getMaxNumeroSequencialCondutor(cpfCondutor)+1));

            formaPagamentoCondutorRepository.save(formaPagamentoCondutor);
            return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoCondutor);
        }
    }

    @GetMapping(value = { "/cpfsequencial/{cpfCondutor}/{numeroSequencialFormaPagamento}" })
    public ResponseEntity<?> consultarFormaPagamentoCondutor(
            @PathVariable String cpfCondutor,
            @PathVariable int numeroSequencialFormaPagamento){

        Optional<FormaPagamentoCondutor> formaPagamentoCondutorOptional = formaPagamentoCondutorRepository.
                findById(new FormaPagamentoCondutorPrimaryKey(
                        new CondutorPrimaryKey(cpfCondutor), numeroSequencialFormaPagamento));

        if (formaPagamentoCondutorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Forma de pagamento não encontrada para o CPF e número sequencial informados.");
        } else {
            return ResponseEntity.ok(formaPagamentoCondutorOptional.get());
        }

    }

    @GetMapping(value = { "/condutor/{cpfCondutor}" })
    public ResponseEntity<?> consultarFormasDePagamentoDeUmCondutor(@PathVariable String cpfCondutor){

        List<FormaPagamentoCondutor> formaPagamentoCondutorList =
                formaPagamentoCondutorRepository.findFormaPagamentoCondutorByCpfCondutor(cpfCondutor);

        if (formaPagamentoCondutorList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Não há formas de pagamento para o CPF de condutor informado.");
        }

        return ResponseEntity.ok(formaPagamentoCondutorList);

    }

    @GetMapping(value = { "/" })
    public ResponseEntity<Collection<FormaPagamentoCondutor>> findAll() {
        var formasPagamento = formaPagamentoCondutorRepository.findAll();
        return ResponseEntity.ok(formasPagamento);
    }

    @DeleteMapping("/{cpfCondutor}/{numeroSequencialFormaPagamento}")
    public ResponseEntity<?> excluirFormaPagamentoCondutor(
            @PathVariable String cpfCondutor,
            @PathVariable int numeroSequencialFormaPagamento) {

        FormaPagamentoCondutorPrimaryKey formaPagamentoCondutorPrimaryKey =
                new FormaPagamentoCondutorPrimaryKey(new CondutorPrimaryKey(cpfCondutor),numeroSequencialFormaPagamento);

        Optional<FormaPagamentoCondutor> formaPagamentoCondutorOptional =
                formaPagamentoCondutorRepository.findById(formaPagamentoCondutorPrimaryKey);

        if (formaPagamentoCondutorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Forma de pagamento não encontrada para o CPF e número sequencial informados.");
        }

        formaPagamentoCondutorRepository.deleteById(formaPagamentoCondutorPrimaryKey);
        return ResponseEntity.ok("Forma de pagamento do condutor excluída com sucesso.");

// TODO: 05/11/2023 implementar verificação de condutor nas tabelas filhas para impedir exclusão
    }

    @PutMapping("/{cpfCondutor}/{numeroSequencialFormaPagamento}")
    public ResponseEntity<?> atualizarFormaPagamentoCondutor(
            @PathVariable String cpfCondutor,
            @PathVariable int numeroSequencialFormaPagamento,
            @RequestBody FormaPagamentoCondutorDto formaPagamentoCondutorDto) {

        Map<Path, String> violacoesMap = validar(formaPagamentoCondutorDto);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {

            FormaPagamentoCondutorPrimaryKey formaPagamentoCondutorPrimaryKey =
                    new FormaPagamentoCondutorPrimaryKey(new CondutorPrimaryKey(cpfCondutor), numeroSequencialFormaPagamento);

            Optional<FormaPagamentoCondutor> formaPagamentoCondutorOptional =
                    formaPagamentoCondutorRepository.findById(formaPagamentoCondutorPrimaryKey);

            if (formaPagamentoCondutorOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body("Forma de pagamento não encontrada para o CPF e número sequencial informados.");
            }

            FormaPagamentoCondutor formaPagamentoCondutor = formaPagamentoCondutorDto.toFormaDePagamentoCondutor();

            FormaPagamentoCondutor formaPagamentoCondutorAtualizado = formaPagamentoCondutorOptional.get();
// TODO: 05/11/2023 Implementar resposta para tentativa de atualização da primary key
            formaPagamentoCondutorAtualizado.setId(formaPagamentoCondutorPrimaryKey);

            Optional<Condutor> optionalCondutor = condutorRespository.
                    findById(formaPagamentoCondutor.getCondutor().getCpfCondutor());
            if (optionalCondutor.isEmpty()){
                return ResponseEntity.badRequest().
                        body("Condutor informado para a atualização da forma de pagamento do condutor não encontrado.");
            }
            formaPagamentoCondutorAtualizado.
                    setCondutor(optionalCondutor.get());
            formaPagamentoCondutorAtualizado.
                    setCodigoTipoFormaPagamento(formaPagamentoCondutor.getCodigoTipoFormaPagamento());
            formaPagamentoCondutorAtualizado.
                    setNumeroCartaoFormaPagamento(formaPagamentoCondutor.getNumeroCartaoFormaPagamento());
            formaPagamentoCondutorAtualizado.
                    setNomeTitularCartaoFormaPagamento(formaPagamentoCondutor.getNomeTitularCartaoFormaPagamento());
            formaPagamentoCondutorAtualizado.
                    setMesValidadeCartaoFormaPagamento(formaPagamentoCondutor.getMesValidadeCartaoFormaPagamento());
            formaPagamentoCondutorAtualizado.
                    setAnoValidadeCartaoFormaPagamento(formaPagamentoCondutor.getAnoValidadeCartaoFormaPagamento());
            formaPagamentoCondutorAtualizado.
                    setValorVerificadorCartaoFormaPagamento(formaPagamentoCondutor.getValorVerificadorCartaoFormaPagamento());
            formaPagamentoCondutorAtualizado.
                    setIndicadorFormaPagamentoPreferida(formaPagamentoCondutor.getIndicadorFormaPagamentoPreferida());

            formaPagamentoCondutorRepository.save(formaPagamentoCondutorAtualizado);
            return ResponseEntity.ok(formaPagamentoCondutorAtualizado);

        }
    }

    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesMap = violacoes.stream()
                .collect(Collectors.toMap(violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesMap;
    }
}
