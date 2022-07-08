package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController // é um bean Controller
@RequestMapping("/pessoa") // localhost:8080/pessoa
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @GetMapping("/hello") // localhost:8080/pessoa/hello
    public String hello() {
        return "Hello world!";
    }

    @PostMapping // localhost:8080/pessoa
    public Pessoa create(@Valid @RequestBody Pessoa pessoa) {
        return pessoaService.create(pessoa);
    }

    @GetMapping // localhost:8080/pessoa
    public List<Pessoa> list() {
        return pessoaService.list();
    }

    @GetMapping("/byname") // localhost:8080/pessoa/byname?nome=Rafa
    public List<Pessoa> listByName(@RequestParam("nome") String nome) {
        return pessoaService.listByName(nome);
    }

    @PutMapping("/{idPessoa}") // localhost:8080/pessoa/1000
    public Pessoa update(@PathVariable("idPessoa") Integer id,
                         @Valid @RequestBody Pessoa pessoaAtualizar) throws Exception {
        return pessoaService.update(id, pessoaAtualizar);
    }

    @DeleteMapping("/{idPessoa}") // localhost:8080/pessoa/10
    public void delete(@PathVariable("idPessoa") Integer id) throws Exception {
        pessoaService.delete(id);
    }
}