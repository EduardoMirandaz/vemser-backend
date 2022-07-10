package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;



    public Contato create(Contato contato, Integer idPessoa) throws Exception {
        Pessoa pessoa1 = pessoaRepository.list().stream()
                .filter(pessoa -> Objects.equals(pessoa.getIdPessoa(),idPessoa))
                .findFirst()
                .orElseThrow( () -> new RegraDeNegocioException("Id de pessoa não cadastrado no sistema!") );
        if(pessoa1 != null){
            contato.setIdPessoa(idPessoa);
            return contatoRepository.create(contato);
        }
        return null;
    }

    public List<Contato> list(){
        return contatoRepository.list();
    }


    // A pessoa poderá alterar as características dos contatos, mas os ids permanecem!
    public Contato update(Integer id, Contato contatoAtualizar) throws Exception {

        Contato contatoRecuperado = procurarContatoPorId(id, "Id de contato não encontrado no sistema!");

        System.out.println(contatoRecuperado);

        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        contatoRecuperado.setNome(contatoAtualizar.getNome());
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());

        return contatoRepository.update(contatoRecuperado);
    }

    private void procurarPessoaPorId(Contato contatoAtualizar, PessoaRepository pessoaRepository) throws Exception {
        pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), contatoAtualizar.getIdPessoa()))
                .findFirst().orElseThrow( () -> new RegraDeNegocioException("Id de pessoa não cadastrado no sistema!") );
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = procurarContatoPorId(id, "Contato não econtrado");
        contatoRepository.delete(contatoRecuperado);
    }

    private Contato procurarContatoPorId(Integer id, String Contato_nao_econtrado) throws Exception {
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException(Contato_nao_econtrado));
    }


    // Lista os contatos da pessoa pelo ID, que é recebido por requestParam ?byUserId=
    public List<Contato> listById(Integer id) throws Exception {
        return contatoRepository.listById(
                contatoRepository.list().stream()
                        .filter(contato -> contato.getIdPessoa().equals(id))
                        .collect(Collectors.toList())
        );
    }
}