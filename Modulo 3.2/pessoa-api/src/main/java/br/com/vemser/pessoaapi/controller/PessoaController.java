package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.Pessoas.*;
import br.com.vemser.pessoaapi.dto.Relacionamentos.PessoaEnderecoDTO;
import br.com.vemser.pessoaapi.dto.Relacionamentos.PessoaPetDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.service.EmailService;
import br.com.vemser.pessoaapi.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Criar nova pessoa", description = "Cria uma nova pessoa e insere no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "PessoaEntity criada"),
                    @ApiResponse(responseCode = "400", description = "Requisicao inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping // localhost:8080/pessoa
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
    public List<PessoaEntity> list() {
        log.info("Listando pessoas!");
        return pessoaService.findAll(); //
    }

    @Operation(summary = "Retornar a pessoa com todos os dados", description = "Retorna a pessoa completa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "PessoaEntity criada"),
                    @ApiResponse(responseCode = "400", description = "Requisicao inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping("/pessoa-completa")// http://localhost:8080/pessoa/pessoa?idPessoa=2
    public List<PessoaCompletaDTO> pessoaCompleta(@RequestParam(value = "idPessoa", required = false) Integer idPessoa ) {
        log.info("Buscando por id da pessoa!");
        return pessoaService.list(idPessoa);
    }


    @GetMapping("/byName")// http://localhost:8080/pessoa/byName?nome=Maicon
    public List<PessoaEntity> findByNome(@RequestParam("nome") String nome) {
        log.info("Buscando por nome!");
        return pessoaService.findByNome(nome);
    }

    @Operation(summary = "Editar pessoa", description = "Edita uma pessoa e mantém ela no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "PessoaEntity editada"),
                    @ApiResponse(responseCode = "400", description = "PessoaEntity nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPessoa}") // localhost:8080/pessoa/1000
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                         @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException, PessoaNulaException {
        log.info("Tentando atualizar pessoa de id ["+id+"]");
        return new ResponseEntity<PessoaDTO>(pessoaService.update(id, pessoaAtualizar), HttpStatus.I_AM_A_TEAPOT);
    }


    @Operation(summary = "Deletar pessoa", description = "Deleta uma pessoa do banco de dados")
    @DeleteMapping("/{idPessoa}") // localhost:8080/pessoa/10
    public void delete(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        log.info("Tentando deletar pessoa de id ["+id+"]");
        pessoaService.delete(id);
    }


    @GetMapping("/pessoa-com-enderecos")
    public ResponseEntity<List<PessoaEnderecoDTO>> listarComEnderecos(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return ResponseEntity.ok(pessoaService.listarComEnderecos(idPessoa));
    }


    @GetMapping("/pessoa-com-contatos")
    public ResponseEntity<List<PessoaContatoDTO>>  listarComContatos(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return ResponseEntity.ok(pessoaService.listarComContatos(idPessoa));
    }

    @GetMapping("/pessoa-com-pet")
    public ResponseEntity<List<PessoaPetDTO>> listarComPets(@RequestParam(value = "idPessoa", required = false) Integer idPessoa) throws RegraDeNegocioException {
        return ResponseEntity.ok(pessoaService.listarComPets(idPessoa));
    }


}