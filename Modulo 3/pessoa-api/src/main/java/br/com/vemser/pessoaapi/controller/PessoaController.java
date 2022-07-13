package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.service.EmailService;
import br.com.vemser.pessoaapi.service.PessoaService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
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

//    @GetMapping("/email")
//    public String sendSimpleMessage(){
//        log.info("Enviando email!");
//        emailService.sendSimpleMessage();
//        return "enviando email utilizando "+ aplicacao;
//    }

//    @SneakyThrows
//    @GetMapping("/imagem")
//    public String sendWithAttachment(){
//        log.info("Tentando enviar imagem!");
//        log.info("Enviando imagem!");
//        emailService.sendWithAttachment();
//        return "enviando imagem utilizando "+ aplicacao;
//
//    }

    @Operation(summary = "Criar nova pessoa", description = "Cria uma nova pessoa e insere no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa criada"),
                    @ApiResponse(responseCode = "400", description = "Requisicao inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping // localhost:8080/pessoa
    @Schema(description = "Criando pessoa")
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoaDTO) throws TipoRequisicaoInvalido {
        log.info("Criando pessoa!");
        return new ResponseEntity<PessoaDTO>(pessoaService.create(pessoaDTO, POST), HttpStatus.CREATED);
    }


    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "400", description = "Não há pessoas a serem exibidas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping // localhost:8080/pessoa
    @Schema(description = "Listando todas as pessoas")
    public List<PessoaDTO> list() {
        log.info("Listando pessoas!");
        return pessoaService.list();
    }



    @Operation(summary = "Listar pessoa por nome", description = "Lista uma pessoa por nome vinda do banco de dados!")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "400", description = "Pessoa nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Listando uma pessoa")
    @GetMapping("/byname") // localhost:8080/pessoa/byname?nome=Rafa
    public List<Pessoa> listByName(@RequestParam("nome") String nome) {
        log.info("Listando resultado da busca por ["+nome+"]");
        return pessoaService.listByName(nome);
    }


    @Operation(summary = "Editar pessoa", description = "Edita uma pessoa e mantém ela no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa editada"),
                    @ApiResponse(responseCode = "400", description = "Pessoa nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Editando uma pessoa")
    @PutMapping("/{idPessoa}") // localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                         @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws PessoaNulaException, RegraDeNegocioException {
        log.info("Tentando atualizar pessoa de id ["+id+"]");
        return new ResponseEntity<PessoaDTO>(pessoaService.update(id, pessoaAtualizar), HttpStatus.I_AM_A_TEAPOT);
    }


    @Operation(summary = "Deletar pessoa", description = "Deleta uma pessoa do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa deletada"),
                    @ApiResponse(responseCode = "400", description = "Pessoa nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Deletando uma pessoa")
    @DeleteMapping("/{idPessoa}") // localhost:8080/pessoa/10
    public void delete(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        log.info("Tentando deletar pessoa de id ["+id+"]");
        pessoaService.delete(id);
    }
}