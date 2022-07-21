package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
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
    public EnderecoDTO update(Integer id, EnderecoCreateDTO enderecoCreateDTOAtualizar) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Atualizando endereco!");

//        PessoaEntity pessoaEntity = pessoaService.findPersonByID(idPessoa);

        // Se o endereco existe ele retorna, senao ele estoura a exception
        EnderecoEntity EnderecoPessoaRecuperado = findAdressByID(id);
        EnderecoEntity EnderecoPessoaAtualizar = objectMapper.convertValue(enderecoCreateDTOAtualizar, EnderecoEntity.class);

//        emailService.sendEmail(pessoaEntity.getNome(), id, pessoaEntity.getEmail(), PUT);


        EnderecoPessoaRecuperado.setTipo(EnderecoPessoaAtualizar.getTipo());
        EnderecoPessoaRecuperado.setLogradouro(EnderecoPessoaAtualizar.getLogradouro());
        EnderecoPessoaRecuperado.setNumero(EnderecoPessoaAtualizar.getNumero());
        EnderecoPessoaRecuperado.setComplemento(EnderecoPessoaAtualizar.getComplemento());
        EnderecoPessoaRecuperado.setCidade(EnderecoPessoaAtualizar.getCidade());
        EnderecoPessoaRecuperado.setEstado(EnderecoPessoaAtualizar.getEstado());
        EnderecoPessoaRecuperado.setPais(EnderecoPessoaAtualizar.getPais());

        EnderecoEntity EnderecoPessoa1 = enderecoRepository.save(EnderecoPessoaRecuperado);

        return objectMapper.convertValue(EnderecoPessoa1, EnderecoDTO.class);
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
