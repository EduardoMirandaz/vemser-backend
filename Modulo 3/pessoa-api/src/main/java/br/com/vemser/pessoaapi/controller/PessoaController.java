package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.service.EmailService;
import br.com.vemser.pessoaapi.service.PessoaService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.vemser.pessoaapi.service.EmailService.POST;
import static br.com.vemser.pessoaapi.service.EmailService.PUT;
import static br.com.vemser.pessoaapi.service.EmailService.DELETE;

@Validated
@RestController // é um bean Controller
@RequestMapping("/pessoa") // localhost:8080/pessoa
@Slf4j
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private EmailService emailService;
    @Value("${user}")
    private String usuario;
    @Value("${spring.application.name}")
    private String aplicacao;

    @GetMapping("/email")
    public String sendSimpleMessage(){
        log.info("Enviando email!");
        emailService.sendSimpleMessage();
        return "enviando email utilizando "+ aplicacao;
    }

    @SneakyThrows
    @GetMapping("/imagem")
    public String sendWithAttachment(){
        log.info("Tentando enviar imagem!");
        log.info("Enviando imagem!");
        emailService.sendWithAttachment();
        return "enviando imagem utilizando "+ aplicacao;

    }


    @PostMapping // localhost:8080/pessoa
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoaDTO) throws TipoRequisicaoInvalido {
        log.info("Criando pessoa!");
        return new ResponseEntity<PessoaDTO>(pessoaService.create(pessoaDTO, POST), HttpStatus.CREATED);
    }

    @GetMapping // localhost:8080/pessoa
    public List<PessoaDTO> list() {
        log.info("Listando pessoas!");
        return pessoaService.list();
    }

    @GetMapping("/byname") // localhost:8080/pessoa/byname?nome=Rafa
    public List<Pessoa> listByName(@RequestParam("nome") String nome) {
        log.info("Listando resultado da busca por ["+nome+"]");
        return pessoaService.listByName(nome);
    }

    @PutMapping("/{idPessoa}") // localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                         @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws PessoaNulaException, RegraDeNegocioException {
        log.info("Tentando atualizar pessoa de id ["+id+"]");
        return new ResponseEntity<PessoaDTO>(pessoaService.update(id, pessoaAtualizar), HttpStatus.I_AM_A_TEAPOT);
    }

    @DeleteMapping("/{idPessoa}") // localhost:8080/pessoa/10
    public void delete(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        log.info("Tentando deletar pessoa de id ["+id+"]");
        pessoaService.delete(id);
    }
}