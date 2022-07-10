package br.com.vemser.pessoaapi.controller;


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
    public List<Endereco> list() {
        log.info("Listando enderecos!");
        return enderecoService.list();
    }

    // deve ser path variable

    @GetMapping("/{id}") // localhost:8080/endereco/id
    public List<Endereco> listById(@PathVariable("id") Integer id) throws Exception {
        log.info("Tentando listar endereco da pessoa de id ["+id+"]");
        return enderecoService.listById(id);
    }

    @PostMapping("/{idPessoa}") // localhost:8080/endereco/6
    public Endereco post(@PathVariable("idPessoa") Integer id, @RequestBody @Valid  Endereco enderecoAdicionar) throws Exception {
        log.info("Tentando inserir novo endereco para a pessoa de id ["+id+"]");
        return enderecoService.create(id, enderecoAdicionar);
    }

    @PutMapping("/{idEndereco}") // localhost:8080/endereco/1000
    public Endereco update(@PathVariable("idEndereco") Integer id, @RequestBody @Valid Endereco enderecoAtualizar) throws Exception {
        log.info("Tentando editar endereco de id ["+id+"]");
        return enderecoService.update(id, enderecoAtualizar);
    }

    @DeleteMapping("/{idEndereco}") // localhost:8080/endereco/10
    public void delete(@PathVariable("idEndereco") Integer id) throws Exception {
        log.info("Tentando deletar endereco de id ["+id+"]");
        enderecoService.delete(id);
    }
}