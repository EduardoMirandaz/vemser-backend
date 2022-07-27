package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.entity.ProfessorEntity;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@Validated
@RestController // é um bean Controller
@RequestMapping("/professor") // localhost:8080/professor
@Slf4j
@RequiredArgsConstructor

public class ProfessorController {

//    @Autowired
//    private ProfessorRepository professorRepository;
//    @Value("${user}")
//    private String usuario;
//    @Value("${spring.application.name}")
//    private String aplicacao;
//
//
//    @PostMapping
//    public ResponseEntity<ProfessorEntity> create(@Valid @RequestBody ProfessorEntity professor) throws TipoRequisicaoInvalido {
//        log.info("Criando professor!");
//        return new ResponseEntity<ProfessorEntity>(professorRepository.save(professor), HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public List<ProfessorEntity> list() {
//        log.info("Listando professores!");
//        return professorRepository.findAll();
//    }

}
