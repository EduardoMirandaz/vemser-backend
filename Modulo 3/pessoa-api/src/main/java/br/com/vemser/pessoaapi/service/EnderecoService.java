package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco create(Endereco Endereco) throws Exception {
        PessoaRepository pessoaRepository = new PessoaRepository();
        pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), Endereco.getIdPessoa()))
                .findFirst().orElseThrow( () -> new Exception("Id de pessoa não cadastrado no sistema!") );
        return enderecoRepository.create(Endereco);

    }

    public List<Endereco> list(){
        return enderecoRepository.list();
    }

    public Endereco update(Integer id, Endereco enderecoAtualizar) throws Exception {
        enderecoRepository.list().stream().filter(Endereco -> Endereco.getIdEndereco().equals(id))
                .findFirst().orElseThrow( () -> new Exception("Id de Endereco não cadastrado no sistema!") );
        PessoaRepository pessoaRepository = new PessoaRepository();
        pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), enderecoAtualizar.getIdPessoa()))
                .findFirst().orElseThrow( () -> new Exception("Id de pessoa não cadastrado no sistema!") );

        Endereco enderecoRecuperado = enderecoRepository.list().stream()
                .filter(Endereco -> Endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereco não econtrado"));
        // nao deu tempo...
//        enderecoRecuperado.setIdPessoa(enderecoAtualizar.getIdPessoa());
//        enderecoRecuperado.setNumero(enderecoAtualizar.getNumero());
//        enderecoRecuperado.setDescricao(enderecoAtualizar.getDescricao());
//        enderecoRecuperado.setNome(enderecoAtualizar.getNome());

        return enderecoRepository.update(enderecoAtualizar);
    }

    public void delete(Integer id) throws Exception {
        Endereco EnderecoRecuperado = enderecoRepository.list().stream()
                .filter(Endereco -> Endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereco não econtrado"));
        enderecoRepository.delete(EnderecoRecuperado);
    }

    public List<Endereco> listById(Integer id) throws Exception {
        return enderecoRepository.listById(
                enderecoRepository.list().stream()
                        .filter(Endereco -> Endereco.getIdPessoa().equals(id))
                        .collect(Collectors.toList())
        );
    }
}
