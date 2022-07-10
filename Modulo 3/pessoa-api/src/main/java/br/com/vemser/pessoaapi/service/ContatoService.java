package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        return contatoRepository.list()
                .stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoDTO create(ContatoCreateDTO contatCreateDTO, Integer idPessoa) throws Exception {
        Contato contato = objectMapper.convertValue(contatCreateDTO, Contato.class);
        pessoaService.findPersonByID(idPessoa);
        contato.setIdPessoa(idPessoa);
        return objectMapper.convertValue(contatoRepository.create(contato), ContatoDTO.class);

    }


    // A pessoa poderá alterar as características dos contatos, mas os ids permanecem!
    public ContatoDTO update(Integer id, ContatoCreateDTO contatoCreateDTOAtualizar) throws Exception {

        Contato contatoRecuperado = procurarContatoPorId(id, "Id de contato não encontrado no sistema!");

        Contato contatoAtualizar = objectMapper.convertValue(contatoCreateDTOAtualizar, Contato.class);

        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        contatoRecuperado.setNome(contatoAtualizar.getNome());
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());

        return objectMapper.convertValue(contatoRepository.update(contatoRecuperado), ContatoDTO.class);
    }

    private void procurarPessoaPorId(Contato contatoAtualizar, PessoaRepository pessoaRepository) throws Exception {
        pessoaRepository.list().stream().filter(pessoa -> Objects.equals(pessoa.getIdPessoa(), contatoAtualizar.getIdPessoa()))
                .findFirst().orElseThrow( () -> new RegraDeNegocioException("Id de pessoa não cadastrado no sistema!") );
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = procurarContatoPorId(id, "Contato não econtrado");
        contatoRepository.delete(contatoRecuperado);
    }

    private Contato procurarContatoPorId(Integer id, String Contato_nao_econtrado) throws Exception {
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException(Contato_nao_econtrado));
    }


    // Lista os contatos da pessoa pelo ID, que é recebido por requestParam ?byUserId=
    public List<ContatoDTO> listById(Integer id) throws Exception {
        List<Contato> contatos = contatoRepository.listById(
                contatoRepository.list()
                        .stream()
                        .filter(contato -> contato.getIdPessoa().equals(id))
                        .collect(Collectors.toList())
        );
        return contatos.stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());

    }
}