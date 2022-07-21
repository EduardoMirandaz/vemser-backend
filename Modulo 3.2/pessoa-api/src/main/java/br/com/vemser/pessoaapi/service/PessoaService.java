package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        PessoaEntity pessoaEntity1 = pessoaRepository.save(pessoaEntity);
//        emailService.sendEmail(pessoaEntity1.getNome(), pessoaEntity1.getidPessoa(), pessoaEntity1.getEmail(), tipoRequisicao);
        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
    }

    public List<PessoaDTO> list(){
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaEntity -> objectMapper.convertValue(pessoaEntity, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public PessoaDTO update(Integer id, PessoaCreateDTO pessoaCreateDTOAtualizar) throws PessoaNulaException, RegraDeNegocioException {
        if(pessoaCreateDTOAtualizar == null){
            throw new PessoaNulaException("Tentou inserir uma pessoa nula!");
        }
        PessoaEntity pessoaEntityAtualizar = objectMapper.convertValue(pessoaCreateDTOAtualizar, PessoaEntity.class);
        PessoaEntity pessoaEntityRecuperada = pessoaRepository.findById(id).orElseThrow( () -> new PessoaNulaException("Pessoa não existe!")  );

        pessoaEntityRecuperada.setCpf(pessoaEntityAtualizar.getCpf());
        pessoaEntityRecuperada.setEmail(pessoaEntityAtualizar.getEmail());
        pessoaEntityRecuperada.setDataNascimento(pessoaEntityAtualizar.getDataNascimento());
        pessoaEntityRecuperada.setNome(pessoaEntityAtualizar.getNome());

        return objectMapper.convertValue(pessoaRepository.save(pessoaEntityRecuperada), PessoaDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = findPersonByID(id);
        pessoaRepository.delete(pessoaEntityRecuperada);
    }

    public PessoaEntity findPersonByID(Integer id) throws RegraDeNegocioException {
        return pessoaRepository.findAll()
                .stream()
                .filter(pessoaEntity -> pessoaEntity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada no banco de dados"));
    }

    public List<PessoaEntity> findByNome(String nome) {
        return pessoaRepository.findByNomeContainsIgnoreCase(nome);
    }



//    public PessoaEntity findByCpf(String cpf) {
//        return pessoaRepository.findByCpf(cpf);
//    }
//
    public List<PessoaEntity> findAll() {
        return pessoaRepository.findAll();
    }

//    public List<PessoaEntity> listPessoaByCPF(String cpf) {
//        return pessoaRepository.listPessoaByCPF(cpf);
//    }

}
