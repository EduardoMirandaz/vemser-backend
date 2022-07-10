package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaService pessoaService;

    public Endereco create(Integer idPessoa, Endereco endereco) throws Exception {
        log.info("Criando pessoa!");
        pessoaService.findPersonByID(idPessoa);
        endereco.setIdPessoa(idPessoa);
        return enderecoRepository.create(endereco);
    }

    public List<Endereco> list(){
        log.info("Listando enderecos");
        return enderecoRepository.list();
    }


    // O endereço é recebido como path variable e altera as informações
    // do mesmo, sem mudar o id da pessoa!
    public Endereco update(Integer id, Endereco enderecoAtualizar) throws Exception {
        log.info("Atualizando endereco!");

        pessoaService.findPersonByID(id);

        // Se o endereco existe ele retorna, senao ele estoura a exception
        Endereco enderecoRecuperado = findAdressByID(id);

        enderecoRecuperado.setTipo(enderecoAtualizar.getTipo());
        enderecoRecuperado.setLogradouro(enderecoAtualizar.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAtualizar.getNumero());
        enderecoRecuperado.setComplemento(enderecoAtualizar.getComplemento());
        enderecoRecuperado.setCidade(enderecoAtualizar.getCidade());
        enderecoRecuperado.setEstado(enderecoAtualizar.getEstado());
        enderecoRecuperado.setPais(enderecoAtualizar.getPais());

        return enderecoRepository.update(enderecoRecuperado);
    }

    public Endereco findAdressByID(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.list().stream()
                .filter(Endereco -> Endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow( () -> new RegraDeNegocioException("Id de Endereco não cadastrado no sistema!") );
    }


    public void delete(Integer id) throws Exception {
        Endereco EnderecoRecuperado = enderecoRepository.list().stream()
                .filter(Endereco -> Endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereco não econtrado"));
        log.info("Deletando endereco!");
        enderecoRepository.delete(EnderecoRecuperado);
    }

    public List<Endereco> listById(Integer id) throws Exception {
        log.info("Listando pessoa pelo id!"+id);
        return enderecoRepository.listById(
                enderecoRepository.list().stream()
                        .filter(Endereco -> Endereco.getIdPessoa().equals(id))
                        .collect(Collectors.toList())
        );
    }
}
