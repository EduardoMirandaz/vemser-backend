package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
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
    public List<ContatoDTO> list() {
        log.info("Tentando listar contatos");
        return contatoService.list();
    }

    @GetMapping("/byUserId") // localhost:8080/contato/byUserId?idPessoa=3
    public List<ContatoDTO> listById(@RequestParam("idPessoa") Integer idPessoa) throws Exception {
        log.info("Tentando listar contatos da pessoa de id ["+idPessoa+"]");
        return contatoService.listById(idPessoa);
    }

    @PostMapping("/{idPessoa}") // localhost:8080/contato/6
    public ContatoDTO post(@PathVariable("idPessoa") Integer idPessoa, @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.info("Tentando inserir novo contato para a pessoa de id ["+idPessoa+"]");
        return contatoService.create(contatoCreateDTO, idPessoa);
    }

    @PutMapping("/{idContato}") // localhost:8080/contato/1000
    public ContatoDTO update(@PathVariable("idContato") Integer idContato, @RequestBody ContatoCreateDTO contatoAtualizar) throws Exception {
        log.info("Tentando editar contato de id ["+idContato+"]");
        return contatoService.update(idContato, contatoAtualizar);
    }

    @DeleteMapping("/{idContato}") // localhost:8080/contato/10
    public void delete(@PathVariable("idContato") Integer id) throws Exception {
        log.info("Tentando deletar contato de id ["+id+"]");
        contatoService.delete(id);
    }
}