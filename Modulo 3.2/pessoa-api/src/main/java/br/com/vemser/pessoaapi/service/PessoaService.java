package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.*;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.entity.PetEntity;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import br.com.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    public List<PessoaDTO> list(){
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaEntity -> objectMapper.convertValue(pessoaEntity, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    // sobrecarga do método list, se passar um id, lista uma pessoa inteira

    public List<PessoaCompletaDTO> list(Integer idPessoa) {
        if (idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
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
        } else {
            return pessoaRepository.findById(idPessoa).stream()
                    .map(pessoaEntity -> {
                        PessoaCompletaDTO pessoaCompleta = objectMapper.convertValue(pessoaEntity, PessoaCompletaDTO.class);
                        pessoaCompleta.setPet(objectMapper.convertValue(pessoaEntity.getPet(), PetDTO.class));
                        pessoaCompleta.setContatos(pessoaEntity.getContatos().stream()
                                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                                .collect(Collectors.toList()));
                        pessoaCompleta.setEnderecos(pessoaEntity.getEnderecos().stream()
                                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                                .collect(Collectors.toList()));
                        return pessoaCompleta;
                    }).toList();
        }
    }

//    SELECT P.ID_PESSOA, P.NOME,EP.ID_ENDERECO, EP.LOGRADOURO, PET.ID_PET, PET.NOME, C.ID_CONTATO, C.NUMERO
//    FROM VEM_SER.PESSOA P
//    LEFT JOIN PESSOA_X_PESSOA_ENDERECO PXPE ON (P.ID_PESSOA = PXPE.ID_PESSOA)
//    LEFT JOIN ENDERECO_PESSOA EP ON (PXPE.ID_ENDERECO = EP.ID_ENDERECO)
//    LEFT JOIN CONTATO C ON (C.ID_PESSOA  = P.ID_PESSOA)
//    LEFT JOIN PET PET ON (PET.ID_PESSOA  = P.ID_PESSOA)

//    @Query("select new br.com.vemser.pessoaapi.dto.PessoaCompletaDTO(" +
//            "           p.idPessoa" +
//            "           p.nome" +
//            "           p.dataNascimento" +
//            "           p.cpf" +
//            "           p.email" +
//            "           c.idContato" +
//            "           c.descricao" +
//            "           c.numero" +
//            "           c.tipoContato" +
//            "           e.idEndereco" +
//            "           e.tipo" +
//            "           e.logradouro" +
//            "           e.numero" +
//            "           e.complemento" +
//            "           e.cep" +
//            "           e.cidade" +
//            "           e.estado" +
//            "           e.pais" +
//            "           pet.idPet" +
//            "           pet.nome" +
//            "           pet.tipoPet" +
//            "   from PessoaDTO p" +
//            "   left join ) \n" +
//    public List<PessoaDTO> list(Integer id){
//        return pessoaRepository.findAll()
//                .stream()
//                .map(pessoaEntity -> objectMapper.convertValue(pessoaEntity, PessoaDTO.class))
//                .collect(Collectors.toList());
//    }


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

//        petRepository.delete(pessoaEntityRecuperada.getPet());
//
//        pessoaEntityRecuperada.getContatos().stream()
//                .peek(c -> contatoRepository.delete(c));
//
//        pessoaEntityRecuperada.getEnderecos().stream()
//                .peek(e -> enderecoRepository.delete(e));

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

    public List<PessoaContatoDTO> listarComContatos(Integer idPessoa) throws RegraDeNegocioException {
        if(idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaContatoDTO pessoasComContato = objectMapper.convertValue(pessoaEntity, PessoaContatoDTO.class);
                        pessoasComContato.setContatos(pessoaEntity.getContatos().stream()
                                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                                .collect(Collectors.toList()));
                        return pessoasComContato;
                    }).toList();
        } else {
            return pessoaRepository.findById(idPessoa).stream()
                    .map(pessoaEntity -> {
                        PessoaContatoDTO pessoaComContato = objectMapper.convertValue(pessoaEntity, PessoaContatoDTO.class);
                        pessoaComContato.setContatos(pessoaEntity.getContatos().stream()
                                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                                .collect(Collectors.toList()));
                        return pessoaComContato;
                    }).toList();
        }
    }

    public List<PessoaEnderecoDTO> listarComEnderecos(Integer idPessoa) throws RegraDeNegocioException {
        if(idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaEnderecoDTO pessoasComEndereco = objectMapper.convertValue(pessoaEntity, PessoaEnderecoDTO.class);
                        pessoasComEndereco.setEnderecos(pessoaEntity.getEnderecos().stream()
                                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                                .collect(Collectors.toList()));
                        return pessoasComEndereco;
                    }).toList();
        } else {
            return pessoaRepository.findById(idPessoa).stream()
                    .map(pessoaEntity -> {
                        PessoaEnderecoDTO pessoaComEndereco = objectMapper.convertValue(pessoaEntity, PessoaEnderecoDTO.class);
                        pessoaComEndereco.setEnderecos(pessoaEntity.getEnderecos().stream()
                                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                                .collect(Collectors.toList()));
                        return pessoaComEndereco;
                    }).toList();
        }
    }

    public List<PessoaPetDTO> listarComPets(Integer idPessoa) throws RegraDeNegocioException {
        if(idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaPetDTO pessoasComPet = objectMapper.convertValue(pessoaEntity, PessoaPetDTO.class);
                        pessoasComPet.setPet(objectMapper.convertValue(pessoaEntity.getPet(), PetDTO.class));
                        return pessoasComPet;
                    }).toList();
        } else {
            return pessoaRepository.findById(idPessoa).stream()
                    .map(pessoaEntity -> {
                        PessoaPetDTO pessoaComPet = objectMapper.convertValue(pessoaEntity, PessoaPetDTO.class);
                        pessoaComPet.setPet(objectMapper.convertValue(pessoaEntity.getPet(), PetDTO.class));
                        return pessoaComPet;
                    }).toList();
        }
    }



}
