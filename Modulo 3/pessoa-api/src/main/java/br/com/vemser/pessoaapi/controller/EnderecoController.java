package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.EmailService;
import br.com.vemser.pessoaapi.service.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.vemser.pessoaapi.service.EmailService.POST;
import static br.com.vemser.pessoaapi.service.EmailService.PUT;
import static br.com.vemser.pessoaapi.service.EmailService.DELETE;

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


    @GetMapping // localhost:8080/contato
    public List<EnderecoDTO> list() {
        log.info("Listando enderecos!");
        return enderecoService.list();
    }

    // deve ser path variable

    @GetMapping("/{id}") // localhost:8080/endereco/id
    public List<EnderecoDTO> listById(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Tentando listar endereco da pessoa de id ["+id+"]");
        return enderecoService.listById(id);
    }

    @PostMapping("/{idPessoa}") // localhost:8080/endereco/6
    public EnderecoDTO post(@PathVariable("idPessoa") Integer id, @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        log.info("Tentando cadastrar enderecos para a pessoa de id ["+id+"]");
        return enderecoService.create(id, enderecoCreateDTO);
    }

    @PutMapping("/{idEndereco}") // localhost:8080/endereco/1000
    public EnderecoDTO update(@PathVariable("idEndereco") Integer id, @RequestBody @Valid EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException {
        log.info("Tentando editar endereco de id ["+id+"]");
        return enderecoService.update(id, enderecoAtualizar);
    }

    @DeleteMapping("/{idEndereco}") // localhost:8080/endereco/10
    public void delete(@PathVariable("idEndereco") Integer id) throws RegraDeNegocioException {
        enderecoService.delete(id);
    }
}