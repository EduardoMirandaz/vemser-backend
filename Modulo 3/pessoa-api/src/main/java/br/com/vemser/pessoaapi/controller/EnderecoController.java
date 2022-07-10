package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.service.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping // localhost:8080/contato
    public List<EnderecoDTO> list() {
        log.info("Listando enderecos!");
        return enderecoService.list();
    }

    // deve ser path variable

    @GetMapping("/{id}") // localhost:8080/endereco/id
    public List<EnderecoDTO> listById(@PathVariable("id") Integer id) throws Exception {
        log.info("Tentando listar endereco da pessoa de id ["+id+"]");
        return enderecoService.listById(id);
    }

    @PostMapping("/{idPessoa}") // localhost:8080/endereco/6
    public EnderecoDTO post(@PathVariable("idPessoa") Integer id, @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.info("Tentando listar enderecos da pessoa de id ["+id+"]");
        return enderecoService.create(id, enderecoCreateDTO);
    }

    @PutMapping("/{idEndereco}") // localhost:8080/endereco/1000
    public EnderecoDTO update(@PathVariable("idEndereco") Integer id, @RequestBody @Valid EnderecoCreateDTO enderecoAtualizar) throws Exception {
        log.info("Tentando editar endereco de id ["+id+"]");
        return enderecoService.update(id, enderecoAtualizar);
    }

    @DeleteMapping("/{idEndereco}") // localhost:8080/endereco/10
    public void delete(@PathVariable("idEndereco") Integer id) throws Exception {
        enderecoService.delete(id);
    }
}