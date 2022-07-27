package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.Endereco.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.Endereco.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailService emailService;

    public EnderecoDTO createEndereco(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        log.info("Criando EnderecoPessoa!");
        EnderecoEntity enderecoPessoa = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        PessoaEntity pessoaEntityRetornadaPorID = pessoaService.findPersonByID(idPessoa);
//        emailService.sendEmail(pessoaEntityRetornadaPorID.getNome(), idPessoa, pessoaEntityRetornadaPorID.getEmail(), POST);
        enderecoPessoa.setPessoas(Set.of(pessoaEntityRetornadaPorID));
        EnderecoEntity enderecoPessoa1 = enderecoRepository.save(enderecoPessoa);
        return objectMapper.convertValue(enderecoPessoa, EnderecoDTO.class);
    }

    // O endereço é recebido como path variable e altera as informações
    // do mesmo, sem mudar o id da pessoa!
    public EnderecoDTO update(Integer id, EnderecoDTO enderecoDTO) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Atualizando endereco!");


        // Se o endereco existe ele retorna, senao ele estoura a exception
        EnderecoEntity enderecoPessoaRecuperadoDoBD = findAdressByID(id);
        EnderecoEntity enderecoPessoaAtualizada = objectMapper.convertValue(enderecoDTO, EnderecoEntity.class);


//        emailService.sendEmail(pessoaEntity.getNome(), id, pessoaEntity.getEmail(), PUT);

        enderecoPessoaRecuperadoDoBD.setTipo(enderecoPessoaAtualizada.getTipo());
        enderecoPessoaRecuperadoDoBD.setLogradouro(enderecoPessoaAtualizada.getLogradouro());
        enderecoPessoaRecuperadoDoBD.setNumero(enderecoPessoaAtualizada.getNumero());
        enderecoPessoaRecuperadoDoBD.setComplemento(enderecoPessoaAtualizada.getComplemento());
        enderecoPessoaRecuperadoDoBD.setCidade(enderecoPessoaAtualizada.getCidade());
        enderecoPessoaRecuperadoDoBD.setEstado(enderecoPessoaAtualizada.getEstado());
        enderecoPessoaRecuperadoDoBD.setPais(enderecoPessoaAtualizada.getPais());

        // Recupero a pessoa e coloco o novo endereço nela.
        PessoaEntity pessoaEntity = pessoaService.findPersonByID(enderecoDTO.getIdPessoa());
        pessoaEntity.setEnderecos(Set.of());

        // Coloco a pessoa no endereço
        enderecoPessoaRecuperadoDoBD.setPessoas(Set.of(pessoaEntity));

        EnderecoEntity enderecoPessoa1 = enderecoRepository.save(enderecoPessoaRecuperadoDoBD);

        return objectMapper.convertValue(enderecoPessoa1, EnderecoDTO.class);
    }

    public EnderecoEntity findAdressByID(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findAll()
                .stream()
                .filter(EnderecoEntity -> EnderecoEntity.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow( () -> new RegraDeNegocioException("Id de EnderecoEntity não cadastrado no sistema!") );
    }


    public EnderecoDTO recuperarEnderecoPorID(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findById(id).stream()
                .map(p -> objectMapper.convertValue(p, EnderecoDTO.class))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereco nao encontrado"));
    }


    public void delete(Integer id) throws RegraDeNegocioException, TipoRequisicaoInvalido {

        log.info("Deletando endereco!");
        enderecoRepository.delete(findAdressByID(id));
    }

    public List<EnderecoDTO> listarTodos() {
        return enderecoRepository.findAll().stream()
                .map(e -> objectMapper.convertValue(e, EnderecoDTO.class))
                .toList();
    }
}
