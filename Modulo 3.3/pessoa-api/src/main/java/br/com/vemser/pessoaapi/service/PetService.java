package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.Pessoas.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.Pessoas.PessoaDTO;
import br.com.vemser.pessoaapi.dto.Pet.PetCreateDTO;
import br.com.vemser.pessoaapi.dto.Pet.PetDTO;
import br.com.vemser.pessoaapi.dto.Pet.PetUpdateDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.entity.PetEntity;
import br.com.vemser.pessoaapi.exceptions.PessoaNulaException;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ObjectMapper objectMapper;

    public List<PetDTO> list() {
        return petRepository.findAll().stream()
                .map(p -> objectMapper.convertValue(p, PetDTO.class))
                .toList();
    }

    public PetDTO create(PetCreateDTO petCreateDTO, int idPessoa) throws RegraDeNegocioException, PessoaNulaException {
        PetEntity petEntity = objectMapper.convertValue(petCreateDTO, PetEntity.class);
        PessoaEntity pessoaEntity = pessoaService.findPersonByID(idPessoa);

        petEntity.setPessoa(pessoaEntity);
        pessoaEntity.setPet(petEntity);

        PessoaDTO savedPerson = pessoaService.update(pessoaEntity.getIdPessoa()
                , objectMapper.convertValue(pessoaEntity, PessoaCreateDTO.class));

        PetEntity save = petRepository.save(petEntity);
        return objectMapper.convertValue(save, PetDTO.class);
    }


    public PetDTO update(Integer idPet, PetUpdateDTO petUpdateDTO) throws RegraDeNegocioException {
        PetEntity petEntityRecuperado = procurarPetPorId(idPet);

        PetEntity petEntityAtualizar = objectMapper.convertValue(petUpdateDTO, PetEntity.class);

        // atualizando os atributos
        petEntityRecuperado.setNome(petEntityAtualizar.getNome());
        petEntityRecuperado.setTipoPet(petEntityAtualizar.getTipoPet());

        // resetando as referencias
        PessoaEntity pessoaRecuperada = pessoaService.findPersonByID(petEntityAtualizar.getIdPessoa());
        // nesse caso, a pessoa pode alterar o dono do pet!
        petEntityRecuperado.setPessoa(pessoaRecuperada);
        petEntityRecuperado.setIdPessoa(petUpdateDTO.getIdPessoa());
        return objectMapper.convertValue(petRepository.save(petEntityRecuperado), PetDTO.class);
    }

    private PetEntity procurarPetPorId(Integer idPet) throws RegraDeNegocioException {
        return petRepository.findAll()
                .stream()
                .filter(petEntity -> petEntity.getIdPet().equals(idPet))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Id de pet não encontrado no sistema!"));
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        try {
            PetEntity petEntityRecuperado = procurarPetPorId(id);
            petRepository.delete(petEntityRecuperado);
        }catch (RegraDeNegocioException e){
            e.printStackTrace();
        }
    }

}
