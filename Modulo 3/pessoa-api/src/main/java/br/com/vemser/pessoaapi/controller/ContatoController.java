package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.service.ContatoService;
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

    @GetMapping // localhost:8080/contato
    public List<Contato> list() {
        return contatoService.list();
    }

    @GetMapping("/byUserId") // localhost:8080/contato/byUserId?idPessoa=3
    public List<Contato> listById(@RequestParam("idPessoa") Integer idPessoa) throws Exception {
        return contatoService.listById(idPessoa);
    }

    @PostMapping("/{idPessoa}") // localhost:8080/contato/6
    public Contato post(@PathVariable("idPessoa") Integer idPessoa, @Valid @RequestBody Contato contatoAdicionar) throws Exception {
        return contatoService.create(contatoAdicionar, idPessoa);
    }

    @PutMapping("/{idContato}") // localhost:8080/contato/1000
    public Contato update(@PathVariable("idContato") Integer idContato, @RequestBody Contato contatoAtualizar) throws Exception {
        return contatoService.update(idContato, contatoAtualizar);
    }

    @DeleteMapping("/{idContato}") // localhost:8080/contato/10
    public void delete(@PathVariable("idContato") Integer id) throws Exception {
        contatoService.delete(id);
    }
}