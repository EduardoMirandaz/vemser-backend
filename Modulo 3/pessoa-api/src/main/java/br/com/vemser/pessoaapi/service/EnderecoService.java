package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    private ObjectMapper objectMapper;

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.info("Criando pessoa!");
        Endereco endereco = objectMapper.convertValue(enderecoCreateDTO, Endereco.class);
        pessoaService.findPersonByID(idPessoa);
        endereco.setIdPessoa(idPessoa);
        return objectMapper.convertValue(enderecoRepository.create(endereco), EnderecoDTO.class);
    }

    public List<EnderecoDTO> list(){
        log.info("Listando enderecos");
        return enderecoRepository.list()
                .stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    // O endereço é recebido como path variable e altera as informações
    // do mesmo, sem mudar o id da pessoa!
    public EnderecoDTO update(Integer id, EnderecoCreateDTO enderecoCreateDTOAtualizar) throws Exception {
        log.info("Atualizando endereco!");

        pessoaService.findPersonByID(id);

        // Se o endereco existe ele retorna, senao ele estoura a exception
        Endereco enderecoRecuperado = findAdressByID(id);

        Endereco enderecoAtualizar = objectMapper.convertValue(enderecoCreateDTOAtualizar, Endereco.class);

        enderecoRecuperado.setTipo(enderecoAtualizar.getTipo());
        enderecoRecuperado.setLogradouro(enderecoAtualizar.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAtualizar.getNumero());
        enderecoRecuperado.setComplemento(enderecoAtualizar.getComplemento());
        enderecoRecuperado.setCidade(enderecoAtualizar.getCidade());
        enderecoRecuperado.setEstado(enderecoAtualizar.getEstado());
        enderecoRecuperado.setPais(enderecoAtualizar.getPais());

        return objectMapper.convertValue(enderecoRepository.update(enderecoRecuperado), EnderecoDTO.class);
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

    public List<EnderecoDTO> listById(Integer id) throws Exception {
        log.info("Listando pessoa pelo id ["+id+"]");
        List<Endereco> enderecos = enderecoRepository.listById(
                enderecoRepository.list()
                        .stream()
                        .filter(endereco -> endereco.getIdPessoa().equals(id))
                        .collect(Collectors.toList())
        );
        return enderecos.stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }
}
