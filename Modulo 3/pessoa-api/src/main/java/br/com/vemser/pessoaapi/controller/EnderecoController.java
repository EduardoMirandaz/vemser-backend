package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco") // localhost:8080/contato
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping // localhost:8080/contato
    public List<Endereco> list() {
        return enderecoService.list();
    }

    @GetMapping("/byId") // localhost:8080/contato/byId?id=3
    public List<Endereco> listById(@RequestParam("id") Integer id) throws Exception {
        return enderecoService.listById(id);
    }

    @PostMapping("/{idPessoa}") // localhost:8080/endereco/6
    public Endereco post(@PathVariable("idPessoa") Integer id, @RequestBody Endereco enderecoAdicionar) throws Exception {
        return enderecoService.create(enderecoAdicionar);
    }

    @PutMapping("/{idEndereco}") // localhost:8080/endereco/1000
    public Endereco update(@PathVariable("idEndereco") Integer id, @RequestBody Endereco enderecoAtualizar) throws Exception {
        return enderecoService.update(id, enderecoAtualizar);
    }

    @DeleteMapping("/{idEndereco}") // localhost:8080/endereco/10
    public void delete(@PathVariable("idEndereco") Integer id) throws Exception {
        enderecoService.delete(id);
    }
}