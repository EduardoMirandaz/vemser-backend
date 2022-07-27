package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.Pet.PetCreateDTO;
import br.com.vemser.pessoaapi.dto.Pet.PetDTO;
import br.com.vemser.pessoaapi.dto.Pet.PetUpdateDTO;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/pet") // localhost:8080/pet
@Slf4j
public class PetController {

    @Autowired
    private PetService petService;


    @Operation(summary = "Listar pets", description = "Lista todas as pets do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pets"),
                    @ApiResponse(responseCode = "400", description = "Não há pets a serem exibidas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping // localhost:8080/contato
    public List<PetDTO> list() {
        log.info("Tentando listar pets");
        return petService.list();
    }


    @Operation(summary = "Criar novo pet", description = "Cria um novo pet e insere no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "ContatoEntity criado"),
                    @ApiResponse(responseCode = "400", description = "Requisicao inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}") // localhost:8080/pet/6
    public PetDTO post(@PathVariable("idPessoa") Integer idPessoa
            , @Valid @RequestBody PetCreateDTO petCreateDTO) throws RegraDeNegocioException, PessoaNulaException {
        log.info("Tentando inserir novo pet para a pessoa de id ["+idPessoa+"]");
        return petService.create(petCreateDTO, idPessoa);
    }

    @Operation(summary = "Editar pet", description = "Edita um pet e mantém ele no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "ContatoEntity editado"),
                    @ApiResponse(responseCode = "400", description = "ContatoEntity nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )


    @PutMapping("/{idPet}") // localhost:8080/pet/1000
    public PetDTO update(@PathVariable("idPet") Integer idPet, @RequestBody PetUpdateDTO petUpdateDTO) throws RegraDeNegocioException {
        log.info("Tentando editar pet de id ["+idPet+"]");
        return petService.update(idPet, petUpdateDTO);
    }


    @Operation(summary = "Deletar pet", description = "Deleta um pet do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pet deletado"),
                    @ApiResponse(responseCode = "400", description = "Pet nao existe"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPet}") // localhost:8080/pet/10
    public void delete(@PathVariable("idPet") Integer id) throws RegraDeNegocioException {
        log.info("Tentando deletar pet de id ["+id+"]");
        petService.delete(id);
    }


}
