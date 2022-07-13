package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;




    public PessoaDTO create(PessoaCreateDTO pessoaCreateDTO, Integer tipoRequisicao) throws TipoRequisicaoInvalido {
        Pessoa pessoa = objectMapper.convertValue(pessoaCreateDTO, Pessoa.class);
        Pessoa pessoa1 = pessoaRepository.create(pessoa);
        emailService.sendEmail(pessoa1.getNome(), pessoa1.getIdPessoa(), pessoa1.getEmail(), tipoRequisicao);
        return objectMapper.convertValue(pessoa, PessoaDTO.class);
    }

    public List<PessoaDTO> list(){
        return pessoaRepository.list()
                .stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public PessoaDTO update(Integer id, PessoaCreateDTO pessoaCreateDTOAtualizar) throws PessoaNulaException, RegraDeNegocioException {
        if(pessoaCreateDTOAtualizar == null){
            throw new PessoaNulaException("Tentou inserir uma pessoa nula!");
        }
        Pessoa pessoaAtualizar = objectMapper.convertValue(pessoaCreateDTOAtualizar, Pessoa.class);
        Pessoa pessoaRecuperada = findPersonByID(id);
        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        return objectMapper.convertValue(pessoaRepository.update(pessoaRecuperada), PessoaDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = findPersonByID(id);
        pessoaRepository.delete(pessoaRecuperada);
    }

    public Pessoa findPersonByID(Integer id) throws RegraDeNegocioException {
        return pessoaRepository.list()
                .stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
    }

    public List<Pessoa> listByName(String nome) {
        return pessoaRepository.listByName(nome);
    }
}