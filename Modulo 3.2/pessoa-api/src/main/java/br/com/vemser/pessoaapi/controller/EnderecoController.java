package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/endereco") // localhost:8080/endereco
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



    // deve ser path variable
//    @Operation(summary = "Listar endereco por nome", description = "Lista uma endereco por nome vinda do banco de dados!")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(responseCode = "200", description = "Retorna a lista de enderecos"),
//                    @ApiResponse(responseCode = "400", description = "PessoaEntity nao existe"),
//                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
//                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
//            }
//    )


    @GetMapping // localhost:8080/endereco
    public List<EnderecoDTO> listarTodos() throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Listando todos os endereços");
        return enderecoService.listarTodos();
    }

    @Operation(summary = "Criar novo endereco", description = "Cria um novo endereco e insere no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereco criada"),
                    @ApiResponse(responseCode = "400", description = "Requisicao inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @Schema(description = "criando endereco")
    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> create(@Valid @RequestBody EnderecoCreateDTO endereco,
                                              @RequestParam("idPessoa")Integer idPessoa) throws RegraDeNegocioException {
        return ResponseEntity.ok(enderecoService.createEndereco(idPessoa, endereco));
    }


    @Operation(summary = "Editar endereco", description = "Edita um endereco e mantém ele no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "EnderecoEntity editado"),
                    @ApiResponse(responseCode = "400", description = "EnderecoEntity nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idEndereco}") // localhost:8080/endereco/1000
    public EnderecoDTO update(@PathVariable("idEndereco") Integer idPessoa, @RequestBody @Valid EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Tentando editar endereco de id ["+idPessoa+"]");
        return enderecoService.update(idPessoa, enderecoAtualizar);
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
    @DeleteMapping("/{idEndereco}") // localhost:8080/endereco/10
    public void delete(@PathVariable("idEndereco") Integer id) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        enderecoService.delete(id);
    }
}