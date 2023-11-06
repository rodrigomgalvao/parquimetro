package com.code4.parquimetro.controller;

import com.code4.parquimetro.controller.dto.CondutorDto;
import com.code4.parquimetro.controller.dto.VeiculoCondutorDto;
import com.code4.parquimetro.dominio.Condutor;
import com.code4.parquimetro.dominio.CondutorPrimaryKey;
import com.code4.parquimetro.dominio.VeiculoCondutor;
import com.code4.parquimetro.dominio.VeiculoCondutorPrimaryKey;
import com.code4.parquimetro.repository.CondutorRespository;
import com.code4.parquimetro.repository.VeiculoCondutorRepository;
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
@RequestMapping("/veiculos")
public class VeiculoCondutorController {
    @Autowired
    private Validator validator;

    @Autowired
    private VeiculoCondutorRepository veiculoCondutorRepository;

    @Autowired
    private CondutorRespository condutorRespository;

    @PostMapping
    public ResponseEntity<?> cadastrarVeiculoCondutor(@RequestBody VeiculoCondutorDto veiculoCondutorDto) {
        Map<Path, String> violacoesMap = validar(veiculoCondutorDto);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {

            VeiculoCondutor veiculoCondutor = veiculoCondutorDto.toVeiculoCondutor();

            Optional<Condutor> optionalCondutor =
                    condutorRespository.findById(veiculoCondutor.getCondutor().getCpfCondutor());

            if (optionalCondutor.isEmpty()){
                return ResponseEntity.badRequest().
                        body("Cadastramento não permitido: CPF do condutor do veículo inexistente na base.");
            }

            Optional<VeiculoCondutor> optionalVeiculoCondutor = veiculoCondutorRepository.
                    findById(veiculoCondutor.getId());

            if (optionalVeiculoCondutor.isPresent()) {
                return ResponseEntity.unprocessableEntity().
                        body("Cadastramento não permitido: Veículo já cadastrado para o condutor informado.");
            }
            veiculoCondutor.setCondutor(optionalCondutor.get());
            veiculoCondutorRepository.save(veiculoCondutor);
            return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCondutor);
        }
    }

    @GetMapping(value = { "/cpfplaca/{cpfCondutor}/{placaVeiculoCondutor}" })
    public ResponseEntity<?> consultarVeiculoCondutor(
            @PathVariable String cpfCondutor,
            @PathVariable String placaVeiculoCondutor){

        Optional<VeiculoCondutor> optionalVeiculoCondutor = veiculoCondutorRepository.
                findById(new VeiculoCondutorPrimaryKey(new CondutorPrimaryKey(cpfCondutor), placaVeiculoCondutor));

        if (optionalVeiculoCondutor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Placa do veículo não encontrada para o CPF do condutor informado.");
        } else {
            return ResponseEntity.ok(optionalVeiculoCondutor.get());
        }

    }

    @GetMapping(value = { "/condutor/{cpfCondutor}" })
    public ResponseEntity<?> consultarVeiculosDeUmCondutor(@PathVariable String cpfCondutor){

        List<VeiculoCondutor> veiculoCondutorList =
                veiculoCondutorRepository.findVeiculoCondutorByCpfCondutor(cpfCondutor);

        if (veiculoCondutorList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Não há veículos para o CPF de condutor informado.");
        }

        return ResponseEntity.ok(veiculoCondutorList);

    }

    @GetMapping(value = { "/placa/{placaVeiculoCondutor}" })
    public ResponseEntity<?> consultarVeiculosDeUmaPlaca(@PathVariable String placaVeiculoCondutor){

        List<VeiculoCondutor> veiculoCondutorList =
                veiculoCondutorRepository.findVeiculoCondutorByPlaca(placaVeiculoCondutor);

        if (veiculoCondutorList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Não há veículos de condutores para a placa informada.");
        }

        return ResponseEntity.ok(veiculoCondutorList);

    }
// TODO: 05/11/2023 Implementar outras consultas

    @GetMapping(value = { "/" })
    public ResponseEntity<Collection<VeiculoCondutor>> findAll() {
        var veiculos = veiculoCondutorRepository.findAll();
        return ResponseEntity.ok(veiculos);
    }

    @DeleteMapping("/{cpfCondutor}/{placaVeiculoCondutor}")
    public ResponseEntity<?> excluirVeiculoCondutor(
            @PathVariable String cpfCondutor,
            @PathVariable String placaVeiculoCondutor) {

        VeiculoCondutorPrimaryKey veiculoCondutorPrimaryKey =
                new VeiculoCondutorPrimaryKey(new CondutorPrimaryKey(cpfCondutor),placaVeiculoCondutor);

        Optional<VeiculoCondutor> optionalVeiculoCondutor =
                veiculoCondutorRepository.findById(veiculoCondutorPrimaryKey);

        if (optionalVeiculoCondutor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Placa do veículo não encontrada para o CPF do condutor informado.");
        } else {
            veiculoCondutorRepository.deleteById(veiculoCondutorPrimaryKey);
            return ResponseEntity.ok("Veículo do condutor excluído com sucesso.");
        }
// TODO: 05/11/2023 implementar verificação de condutor nas tabelas filhas para impedir exclusão
    }

    @PutMapping("/{cpfCondutor}/{placaVeiculoCondutor}")
    public ResponseEntity<?> atualizarCondutor(
            @PathVariable String cpfCondutor,
            @PathVariable String placaVeiculoCondutor,
            @RequestBody VeiculoCondutorDto veiculoCondutorDto) {

        Map<Path, String> violacoesMap = validar(veiculoCondutorDto);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {

            VeiculoCondutorPrimaryKey veiculoCondutorPrimaryKey =
                    new VeiculoCondutorPrimaryKey(new CondutorPrimaryKey(cpfCondutor), placaVeiculoCondutor);

            Optional<VeiculoCondutor> veiculoCondutorExistente =
                    veiculoCondutorRepository.findById(veiculoCondutorPrimaryKey);

            if (veiculoCondutorExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body("Placa do veículo não encontrada para o CPF do condutor informado.");
            }

            VeiculoCondutor veiculoCondutor = veiculoCondutorDto.toVeiculoCondutor();

            VeiculoCondutor veiculoCondutorAtualizado = veiculoCondutorExistente.get();
// TODO: 05/11/2023 Implementar resposta para tentativa de atualização da primary key
            veiculoCondutorAtualizado.setId(veiculoCondutor.getId());

            Optional<Condutor> optionalCondutor = condutorRespository.
                    findById(veiculoCondutor.getCondutor().getCpfCondutor());
            if (optionalCondutor.isEmpty()){
                return ResponseEntity.badRequest().
                        body("Condutor informado para a atualização do veículo do condutor não encontrado.");
            }
            veiculoCondutorAtualizado.setCondutor(optionalCondutor.get());
            veiculoCondutorAtualizado.setMarcaVeiculoCondutor(veiculoCondutor.getMarcaVeiculoCondutor());
            veiculoCondutorAtualizado.setModeloVeiculoCondutor(veiculoCondutor.getModeloVeiculoCondutor());
            veiculoCondutorAtualizado.setCorVeiculoCondutor(veiculoCondutor.getCorVeiculoCondutor());
            veiculoCondutorAtualizado.setAnoFabricacaoVeiculoCondutor(veiculoCondutor.getAnoFabricacaoVeiculoCondutor());
            veiculoCondutorAtualizado.setAnoModeloVeiculoCondutor(veiculoCondutor.getAnoModeloVeiculoCondutor());

            veiculoCondutorRepository.save(veiculoCondutorAtualizado);
            return ResponseEntity.ok(veiculoCondutorAtualizado);

        }
    }
    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesMap = violacoes.stream()
                .collect(Collectors.toMap(violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesMap;
    }
}