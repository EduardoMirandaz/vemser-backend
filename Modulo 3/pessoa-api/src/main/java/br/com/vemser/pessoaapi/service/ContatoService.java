package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

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

        Contato contatoRecuperado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não econtrado"));

        contatoRecuperado.setIdPessoa(contatoAtualizar.getIdPessoa());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        contatoRecuperado.setNome(contatoAtualizar.getNome());

        return contatoRepository.update(contatoAtualizar);
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não econtrado"));
        contatoRepository.delete(contatoRecuperado);
    }

    public List<Contato> listById(Integer id) throws Exception {
        return contatoRepository.listById(
                contatoRepository.list().stream()
                        .filter(contato -> contato.getIdPessoa().equals(id))
                        .collect(Collectors.toList())
        );
    }
}