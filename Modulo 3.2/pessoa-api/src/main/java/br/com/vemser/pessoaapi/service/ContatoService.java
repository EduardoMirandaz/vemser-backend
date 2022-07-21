package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
import br.com.vemser.pessoaapi.entity.ContatoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.entity.enums.TipoContato;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private ObjectMapper objectMapper;

    public List<ContatoDTO> list(){
        return contatoRepository.findAll().stream()
                .map(c -> objectMapper.convertValue(c, ContatoDTO.class))
                .toList();
    }

    public ContatoDTO create(ContatoCreateDTO contatoCreateDTO, Integer idPessoa) throws RegraDeNegocioException {
        ContatoEntity contatoEntity = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        PessoaEntity pessoaEntity = pessoaService.findPersonByID(idPessoa);
        contatoEntity.setPessoa(pessoaEntity);
        ContatoEntity save = contatoRepository.save(contatoEntity);
        return objectMapper.convertValue(save, ContatoDTO.class);

    }


    // A pessoa poderá alterar as características dos contatos, mas os ids permanecem!
    public ContatoDTO update(Integer id, ContatoCreateDTO contatoCreateDTOAtualizar) throws RegraDeNegocioException {

        ContatoEntity contatoEntityRecuperado = procurarContatoPorId(id, "Id de contato não encontrado no sistema!");

        ContatoEntity contatoEntityAtualizar = objectMapper.convertValue(contatoCreateDTOAtualizar, ContatoEntity.class);

        contatoEntityRecuperado.setNumero(contatoEntityAtualizar.getNumero());
        contatoEntityRecuperado.setDescricao(contatoEntityAtualizar.getDescricao());
        contatoEntityRecuperado.setTipoContato(contatoEntityAtualizar.getTipoContato());

        return objectMapper.convertValue(contatoRepository.save(contatoEntityRecuperado), ContatoDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ContatoEntity contatoEntityRecuperado = procurarContatoPorId(id, "ContatoEntity não encontrado");
        contatoRepository.delete(contatoEntityRecuperado);
    }

    private ContatoEntity procurarContatoPorId(Integer id, String Contato_nao_encontrado) throws RegraDeNegocioException {
        return contatoRepository.findAll()
                .stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException(Contato_nao_encontrado));
    }

    public List<ContatoEntity> listarPessoasPorTipoDeContato(TipoContato tipoContato) {
        return contatoRepository.findAll().stream()
                .filter(contatoEntity -> {
                    return contatoEntity.getTipoContato().equals(tipoContato);
                }).collect(Collectors.toList());
    }
}