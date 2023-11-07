package com.code4.parquimetro.controller;

import com.code4.parquimetro.controller.dto.CondutorDto;
import com.code4.parquimetro.dominio.Condutor;
import com.code4.parquimetro.dominio.CondutorPrimaryKey;
import com.code4.parquimetro.repository.CondutorRespository;
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
@RequestMapping("/condutores")
public class CondutorController {
    @Autowired
    private Validator validator;

    @Autowired
    private CondutorRespository condutorRespository;

    @PostMapping
    public ResponseEntity<?> cadastrarCondutor(@RequestBody CondutorDto condutorDto) {
        Map<Path, String> violacoesMap = validar(condutorDto);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {

            Condutor condutor = condutorDto.toCondutor();

            Optional<Condutor> optionalCondutor = condutorRespository.findById(condutor.getCpfCondutor());

            if (optionalCondutor.isPresent()) {
                return ResponseEntity.unprocessableEntity().
                        body("Cadastramento não permitido: CPF do condutor já encontrado na base de dados.");
            }

            condutorRespository.save(condutor);
            return ResponseEntity.status(HttpStatus.CREATED).body(condutor);
        }
    }

    @GetMapping(value = { "/cpf/{cpf}" })
    public ResponseEntity<?> consultarCondutorCpf(@PathVariable String cpf){

        Optional<Condutor> optionalCondutor = condutorRespository.findById(new CondutorPrimaryKey(cpf));

        if (optionalCondutor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Condutor não encontrado para o CPF informado.");
        } else {
            return ResponseEntity.ok(optionalCondutor.get());
        }

    }
// TODO: 05/11/2023 Implementar outras consultas

    @GetMapping(value = { "/" })
    public ResponseEntity<Collection<Condutor>> findAll() {
        var condutores = condutorRespository.findAll();
        return ResponseEntity.ok(condutores);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> excluirCondutorCpf(@PathVariable String cpf) {

        CondutorPrimaryKey condutorPrimaryKey = new CondutorPrimaryKey(cpf);

        Optional<Condutor> optionalCondutor = condutorRespository.findById(condutorPrimaryKey);

        if (optionalCondutor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor não encontrado para exclusão.");
        } else {
            condutorRespository.deleteById(condutorPrimaryKey);
            return ResponseEntity.ok("Condutor excluído com sucesso.");
        }
// TODO: 05/11/2023 implementar verificação de condutor nas tabelas filhas para impedir exclusão
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> atualizarCondutor(
            @PathVariable String cpf,
            @RequestBody CondutorDto condutorDto) {
        Map<Path, String> violacoesMap = validar(condutorDto);

        CondutorPrimaryKey condutorPrimaryKey = new CondutorPrimaryKey(cpf);

        if (!violacoesMap.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesMap);
        } else {
            Optional<Condutor> condutorExistente = condutorRespository.findById(condutorPrimaryKey);

            if (condutorExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor não encontrado para atualização.");
            } else {
                Condutor condutor = condutorDto.toCondutor();

                Condutor condutorAtualizado = condutorExistente.get();
// TODO: 05/11/2023 Implementar resposta para tentativa de atualização da primary key
                condutorAtualizado.setCpfCondutor(condutorPrimaryKey);
                condutorAtualizado.setNomeCondutor(condutor.getNomeCondutor());
                condutorAtualizado.setDdiTelefoneCondutor(condutor.getDdiTelefoneCondutor());
                condutorAtualizado.setDddTelefoneCondutor(condutor.getDddTelefoneCondutor());
                condutorAtualizado.setNumeroTelefoneCondutor(condutor.getNumeroTelefoneCondutor());
                condutorAtualizado.setEmailCondutor(condutor.getEmailCondutor());
                condutorAtualizado.setLogradouroEnderecoCondutor(condutor.getLogradouroEnderecoCondutor());
                condutorAtualizado.setBairroEnderecoCondutor(condutor.getBairroEnderecoCondutor());
                condutorAtualizado.setCidadeEnderecoCondutor(condutor.getCidadeEnderecoCondutor());
                condutorAtualizado.setSiglaUfEnderecoCondutor(condutor.getSiglaUfEnderecoCondutor());

                condutorRespository.save(condutorAtualizado);
                return ResponseEntity.ok(condutorAtualizado);
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
