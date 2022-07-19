package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/contato") // localhost:8080/contato
@Slf4j
public class ContatoController {

    @Autowired
    private ContatoService contatoService;


    @Operation(summary = "Listar contatos", description = "Lista todas as contatos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de contatos"),
                    @ApiResponse(responseCode = "400", description = "Não há contatos a serem exibidas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping // localhost:8080/contato
    public List<ContatoDTO> list() {
        log.info("Tentando listar contatos");
        return contatoService.list();
    }


    @Operation(summary = "Listar contato por nome", description = "Lista uma contato por nome vinda do banco de dados!")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de contatos"),
                    @ApiResponse(responseCode = "400", description = "Pessoa nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/byUserId") // localhost:8080/contato/byUserId?idPessoa=3
    public List<ContatoDTO> listById(@RequestParam("idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        log.info("Tentando listar contatos da pessoa de id ["+idPessoa+"]");
        return contatoService.listById(idPessoa);
    }

    @Operation(summary = "Criar novo contato", description = "Cria um novo contato e insere no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato criado"),
                    @ApiResponse(responseCode = "400", description = "Requisicao inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}") // localhost:8080/contato/6
    public ContatoDTO post(@PathVariable("idPessoa") Integer idPessoa, @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        log.info("Tentando inserir novo contato para a pessoa de id ["+idPessoa+"]");
        return contatoService.create(contatoCreateDTO, idPessoa);
    }

    @Operation(summary = "Editar contato", description = "Edita um contato e mantém ele no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato editado"),
                    @ApiResponse(responseCode = "400", description = "Contato nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idContato}") // localhost:8080/contato/1000
    public ContatoDTO update(@PathVariable("idContato") Integer idContato, @RequestBody ContatoCreateDTO contatoAtualizar) throws RegraDeNegocioException {
        log.info("Tentando editar contato de id ["+idContato+"]");
        return contatoService.update(idContato, contatoAtualizar);
    }


    @Operation(summary = "Deletar contato", description = "Deleta um contato do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contato deletado"),
                    @ApiResponse(responseCode = "400", description = "Contato nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idContato}") // localhost:8080/contato/10
    public void delete(@PathVariable("idContato") Integer id) throws RegraDeNegocioException {
        log.info("Tentando deletar contato de id ["+id+"]");
        contatoService.delete(id);
    }
}