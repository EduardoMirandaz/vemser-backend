package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/contato") // localhost:8080/contato
public class ContatoController {

    @Autowired
    private ContatoService contatoService;


    @GetMapping // localhost:8080/contato
    public List<Contato> list() {
        return contatoService.list();
    }

    @GetMapping("/byId") // localhost:8080/contato/byId?id=3
    public List<Contato> listById(@RequestParam("id") Integer id) throws Exception {
        return contatoService.listById(id);
    }

    @PostMapping("/{idPessoa}") // localhost:8080/contato/6
    public Contato post(@PathVariable("idPessoa") Integer id, @Valid @RequestBody Contato contatoAdicionar) throws Exception {
        return contatoService.create(contatoAdicionar);
    }

    @PutMapping("/{idContato}") // localhost:8080/contato/1000
    public Contato update(@PathVariable("idContato") Integer id, @Valid @RequestBody Contato contatoAtualizar) throws Exception {
        return contatoService.update(id, contatoAtualizar);
    }

    @DeleteMapping("/{idContato}") // localhost:8080/contato/10
    public void delete(@PathVariable("idContato") Integer id) throws Exception {
        contatoService.delete(id);
    }
}