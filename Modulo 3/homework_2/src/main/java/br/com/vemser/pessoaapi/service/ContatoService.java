package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;

import java.util.List;
import java.util.Objects;

public class ContatoService {

    private ContatoRepository contatoRepository;

    public ContatoService(){
        contatoRepository = new ContatoRepository();
    }

    public Contato create(Contato contato) throws Exception {
        PessoaRepository pessoaRepository = new PessoaRepository();
        pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), contato.getIdPessoa()))
                .findFirst().orElseThrow( () -> new Exception("Id de pessoa não cadastrado no sistema!") );
        return contatoRepository.create(contato);
    }

    public List<Contato> list(){
        return contatoRepository.list();
    }

    public Contato update(Integer id, Contato contatoAtualizar) throws Exception {
        contatoRepository.list().stream().filter(contato -> contato.getIdContato().equals(id))
                .findFirst().orElseThrow( () -> new Exception("Id de contato não cadastrado no sistema!") );
        PessoaRepository pessoaRepository = new PessoaRepository();
        pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), contatoAtualizar.getIdPessoa()))
                .findFirst().orElseThrow( () -> new Exception("Id de pessoa não cadastrado no sistema!") );
        return contatoRepository.update(id, contatoAtualizar);
    }

    public void delete(Integer id) throws Exception {
        contatoRepository.delete(id);
    }

    public List<Contato> listById(Integer id) throws Exception {
        return contatoRepository.listById(id);
    }
}