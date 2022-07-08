package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.Pessoa;
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
        Pessoa pessoa1 = pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), contato.getIdPessoa()))
                .findFirst().orElseThrow( () -> new Exception("Id de pessoa não cadastrado no sistema!") );
        if(pessoa1 != null){
            return contatoRepository.create(contato);
        }
        return null;

    }

    public List<Contato> list(){
        System.out.println(contatoRepository.list());
        return contatoRepository.list();
    }

    public Contato update(Integer id, Contato contatoAtualizar) throws Exception {
        procurarContatoPorId(id, "Id de contato não cadastrado no sistema!");
        PessoaRepository pessoaRepository = new PessoaRepository();
        procurarPessoaPorId(contatoAtualizar, pessoaRepository);

        Contato contatoRecuperado = procurarContatoPorId(id, "Contato não econtrado");

        contatoRecuperado.setIdContato(id);
        contatoRecuperado.setIdPessoa(contatoAtualizar.getIdPessoa());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        contatoRecuperado.setNome(contatoAtualizar.getNome());

        return contatoRepository.update(contatoRecuperado);
    }

    private void procurarPessoaPorId(Contato contatoAtualizar, PessoaRepository pessoaRepository) throws Exception {
        pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), contatoAtualizar.getIdPessoa()))
                .findFirst().orElseThrow( () -> new Exception("Id de pessoa não cadastrado no sistema!") );
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = procurarContatoPorId(id, "Contato não econtrado");
        contatoRepository.delete(contatoRecuperado);
    }

    private Contato procurarContatoPorId(Integer id, String Contato_nao_econtrado) throws Exception {
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception(Contato_nao_econtrado));
    }

    public List<Contato> listById(Integer id) throws Exception {
        return contatoRepository.listById(
                contatoRepository.list().stream()
                        .filter(contato -> contato.getIdPessoa().equals(id))
                        .collect(Collectors.toList())
        );
    }
}