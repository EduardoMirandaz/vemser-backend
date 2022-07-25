package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.Contato.ContatoDTO;
import br.com.vemser.pessoaapi.dto.Endereco.EnderecoDTO;
import br.com.vemser.pessoaapi.dto.Pessoas.*;
import br.com.vemser.pessoaapi.dto.Pet.PetDTO;
import br.com.vemser.pessoaapi.dto.Relacionamentos.PessoaEnderecoDTO;
import br.com.vemser.pessoaapi.dto.Relacionamentos.PessoaPetDTO;
import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import br.com.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;


    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;


    @Autowired
    private ObjectMapper objectMapper;




    public PessoaDTO create(PessoaCreateDTO pessoaCreateDTO, Integer tipoRequisicao) throws TipoRequisicaoInvalido {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        PessoaEntity pessoaEntity1 = pessoaRepository.save(pessoaEntity);
//        emailService.sendEmail(pessoaEntity1.getNome(), pessoaEntity1.getidPessoa(), pessoaEntity1.getEmail(), tipoRequisicao);
        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
    }

    public Page<PessoaEntity> listPaginado(Integer paginaQueEuQuero,
                                           Integer quantidadeDeRegistrosPorPagina){
        Pageable pageable = PageRequest.of(paginaQueEuQuero, quantidadeDeRegistrosPorPagina);
        return pessoaRepository.findAll(pageable);

    }

    public List<PessoaCompletaDTO> list(Integer idPessoa) {
        if (idPessoa == null)
        {
            return mapPessoaEntityParaCompletaDto(pessoaRepository.findAll().stream());
        }
        else
        {
            return mapPessoaEntityParaCompletaDto(pessoaRepository.findById(idPessoa).stream());
        }
    }

    List<PessoaCompletaDTO> mapPessoaEntityParaCompletaDto(Stream<PessoaEntity> pE){
            return pE.map(pessoaEntity -> {
            PessoaCompletaDTO pessoasCompletas = objectMapper.convertValue(pessoaEntity, PessoaCompletaDTO.class);
            pessoasCompletas.setPet(objectMapper.convertValue(pessoaEntity.getPet(), PetDTO.class));
            pessoasCompletas.setContatos(pessoaEntity.getContatos().stream()
                    .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                    .collect(Collectors.toList()));
            pessoasCompletas.setEnderecos(pessoaEntity.getEnderecos().stream()
                    .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                    .collect(Collectors.toList()));
            return pessoasCompletas;
        }).toList();

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

        petRepository.delete(pessoaEntityRecuperada.getPet());

//        pessoaEntityRecuperada.getContatos().stream()
//                .peek(c -> contatoRepository.delete(c));
//
//        pessoaEntityRecuperada.getEnderecos().stream()
//                .peek(e -> enderecoRepository.delete(e));

        pessoaRepository.delete(pessoaEntityRecuperada);
    }

    public PessoaEntity findPersonByID(Integer id) throws RegraDeNegocioException {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada no banco de dados"));
    }

    public List<PessoaEntity> findByNome(String nome) {
        return pessoaRepository.findByNomeContainsIgnoreCase(nome);
    }
    public List<PessoaEntity> findAll() {
        return pessoaRepository.findAll();
    }

    public List<PessoaContatoDTO> listarComContatos(Integer idPessoa) throws RegraDeNegocioException {
        if(idPessoa == null)
        {
            return mapPessoaContatoEntityToDto(pessoaRepository.findAll().stream());
        }
        else
        {
            return mapPessoaContatoEntityToDto(pessoaRepository.findById(idPessoa).stream());
        }
    }

    public List<PessoaEnderecoDTO> listarComEnderecos(Integer idPessoa) throws RegraDeNegocioException {
        if(idPessoa == null)
        {
            return mapPessoaEnderecoEntityToDto(pessoaRepository.findAll().stream());
        }
        else
        {
            return mapPessoaEnderecoEntityToDto(pessoaRepository.findById(idPessoa).stream());
        }
    }
    public List<PessoaPetDTO> listarComPets(Integer idPessoa) throws RegraDeNegocioException {
        if(idPessoa == null)
        {
            return mapPessoaPetEntityToDto(pessoaRepository.findAll().stream());
        }
        else
        {
            return mapPessoaPetEntityToDto(pessoaRepository.findById(idPessoa).stream());
        }
    }

    public List<PessoaEnderecoDTO> mapPessoaEnderecoEntityToDto(Stream<PessoaEntity> peDto){
        return peDto.map(pessoaEntity -> {
            PessoaEnderecoDTO pessoaComEndereco = objectMapper.convertValue(pessoaEntity, PessoaEnderecoDTO.class);
            pessoaComEndereco.setEnderecos(pessoaEntity.getEnderecos().stream()
                    .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                    .collect(Collectors.toList()));
            return pessoaComEndereco;
        }).toList();
    }

    public List<PessoaPetDTO> mapPessoaPetEntityToDto(Stream<PessoaEntity> peDto){
            return peDto.map(pessoaEntity -> {
            PessoaPetDTO pessoaComPet = objectMapper.convertValue(pessoaEntity, PessoaPetDTO.class);
            pessoaComPet.setPet(objectMapper.convertValue(pessoaEntity.getPet(), PetDTO.class));
            return pessoaComPet;
        }).toList();
    }

    public List<PessoaContatoDTO> mapPessoaContatoEntityToDto(Stream<PessoaEntity> peDto){
        return peDto.map(pessoaEntity -> {
            PessoaContatoDTO pessoaComContato = objectMapper.convertValue(pessoaEntity, PessoaContatoDTO.class);
            pessoaComContato.setContatos(pessoaEntity.getContatos().stream()
                    .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                    .collect(Collectors.toList()));
            return pessoaComContato;
        }).toList();
    }

}
