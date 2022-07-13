package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.service.EmailService;
import br.com.vemser.pessoaapi.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/endereco") // localhost:8080/contato
@Slf4j
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EmailService emailService;
    @Value("${user}")
    private String usuario;
    @Value("${spring.application.name}")
    private String aplicacao;


    @Operation(summary = "Listar enderecos", description = "Lista todas as enderecos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de enderecos"),
                    @ApiResponse(responseCode = "400", description = "Não há enderecos a serem exibidas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Listando todos os enderecos")
    @GetMapping // localhost:8080/contato
    public List<EnderecoDTO> list() {
        log.info("Listando enderecos!");
        return enderecoService.list();
    }

    // deve ser path variable

    @Operation(summary = "Listar endereco por nome", description = "Lista uma endereco por nome vinda do banco de dados!")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de enderecos"),
                    @ApiResponse(responseCode = "400", description = "Pessoa nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Listando um endereco")
    @GetMapping("/{id}") // localhost:8080/endereco/id
    public List<EnderecoDTO> listById(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Tentando listar endereco da pessoa de id ["+id+"]");
        return enderecoService.listById(id);
    }


    @Operation(summary = "Criar novo endereco", description = "Cria um novo endereco e insere no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereco criado"),
                    @ApiResponse(responseCode = "400", description = "Requisicao inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Criando endereco")
    @PostMapping("/{idPessoa}") // localhost:8080/endereco/6
    public EnderecoDTO post(@PathVariable("idPessoa") Integer id, @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Tentando cadastrar enderecos para a pessoa de id ["+id+"]");
        return enderecoService.create(id, enderecoCreateDTO);
    }


    @Operation(summary = "Editar endereco", description = "Edita um endereco e mantém ele no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereco editado"),
                    @ApiResponse(responseCode = "400", description = "Endereco nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Editando um endereco")
    @PutMapping("/{idEndereco}") // localhost:8080/endereco/1000
    public EnderecoDTO update(@PathVariable("idEndereco") Integer id, @RequestBody @Valid EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Tentando editar endereco de id ["+id+"]");
        return enderecoService.update(id, enderecoAtualizar);
    }


    @Operation(summary = "Deletar endereco", description = "Deleta um endereco do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereco deletado"),
                    @ApiResponse(responseCode = "400", description = "Endereco nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "Deletando um endereco")
    @DeleteMapping("/{idEndereco}") // localhost:8080/endereco/10
    public void delete(@PathVariable("idEndereco") Integer id) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        enderecoService.delete(id);
    }
}