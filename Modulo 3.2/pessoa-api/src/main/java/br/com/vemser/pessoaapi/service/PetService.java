package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.vemser.pessoaapi.dto.PetDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.entity.PetEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    ObjectMapper objectMapper;

    public List<PetDTO> list() {
        return petRepository.findAll().stream()
                .map(p -> objectMapper.convertValue(p, PetDTO.class))
                .toList();
    }

    public PetDTO create(PetCreateDTO petCreateDTO, int idPessoa) throws RegraDeNegocioException {
        PetEntity petEntity = objectMapper.convertValue(petCreateDTO, PetEntity.class);
        PessoaEntity pessoaEntity = pessoaService.findPersonByID(idPessoa);
        petEntity.setPessoa(pessoaEntity);
        PetEntity save = petRepository.save(petEntity);
        return objectMapper.convertValue(save, PetDTO.class);
    }


    public PetDTO update(Integer idPet, PetCreateDTO petCreateDTOAtualizar) throws RegraDeNegocioException {
        PetEntity petEntityRecuperado = procurarPetPorId(idPet);

        PetEntity petEntityAtualizar = objectMapper.convertValue(petCreateDTOAtualizar, PetEntity.class);

        // atualizando os atributos
        petEntityRecuperado.setNome(petEntityAtualizar.getNome());
        petEntityRecuperado.setTipoPet(petEntityAtualizar.getTipoPet());

        // resetando as referencias
        PessoaEntity pessoaRecuperada = pessoaService.findPersonByID(petCreateDTOAtualizar.getIdPessoa());
        // nesse caso, a pessoa pode alterar o dono do pet!
        petEntityRecuperado.setPessoa(pessoaRecuperada);

        return objectMapper.convertValue(petRepository.save(petEntityRecuperado), PetDTO.class);
    }

    private PetEntity procurarPetPorId(Integer idPet) throws RegraDeNegocioException {
        return petRepository.findAll()
                .stream()
                .filter(petEntity -> petEntity.getIdPet().equals(idPet))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Id de pet não encontrado no sistema!"));
    }
}
