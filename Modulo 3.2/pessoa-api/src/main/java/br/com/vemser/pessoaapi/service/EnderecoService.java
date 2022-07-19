package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.ENDERECO_PESSOA;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static br.com.vemser.pessoaapi.service.EmailService.*;

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

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Criando ENDERECOPESSOA!");
        ENDERECO_PESSOA ENDERECOPESSOA = objectMapper.convertValue(enderecoCreateDTO, ENDERECO_PESSOA.class);
        Pessoa pessoaRetornadaPorID = pessoaService.findPersonByID(idPessoa);
//        emailService.sendEmail(pessoaRetornadaPorID.getNome(), idPessoa, pessoaRetornadaPorID.getEmail(), POST);
        ENDERECOPESSOA.setIdPessoa(idPessoa);
        ENDERECO_PESSOA ENDERECOPESSOA1 = enderecoRepository.save(ENDERECOPESSOA);
        return objectMapper.convertValue(ENDERECOPESSOA, EnderecoDTO.class);
    }

    // O endereço é recebido como path variable e altera as informações
    // do mesmo, sem mudar o id da pessoa!
    public EnderecoDTO update(Integer id, EnderecoCreateDTO enderecoCreateDTOAtualizar) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Atualizando endereco!");

        Integer idPessoa = findAdressByID(id).getIdPessoa();
        Pessoa pessoa = pessoaService.findPersonByID(idPessoa);

        // Se o endereco existe ele retorna, senao ele estoura a exception
        ENDERECO_PESSOA ENDERECOPESSOARecuperado = findAdressByID(id);
        ENDERECO_PESSOA ENDERECOPESSOAAtualizar = objectMapper.convertValue(enderecoCreateDTOAtualizar, ENDERECO_PESSOA.class);

//        emailService.sendEmail(pessoa.getNome(), id, pessoa.getEmail(), PUT);


        ENDERECOPESSOARecuperado.setTipo(ENDERECOPESSOAAtualizar.getTipo());
        ENDERECOPESSOARecuperado.setLogradouro(ENDERECOPESSOAAtualizar.getLogradouro());
        ENDERECOPESSOARecuperado.setNumero(ENDERECOPESSOAAtualizar.getNumero());
        ENDERECOPESSOARecuperado.setComplemento(ENDERECOPESSOAAtualizar.getComplemento());
        ENDERECOPESSOARecuperado.setCidade(ENDERECOPESSOAAtualizar.getCidade());
        ENDERECOPESSOARecuperado.setEstado(ENDERECOPESSOAAtualizar.getEstado());
        ENDERECOPESSOARecuperado.setPais(ENDERECOPESSOAAtualizar.getPais());

        ENDERECO_PESSOA ENDERECOPESSOA1 = enderecoRepository.save(ENDERECOPESSOARecuperado);

        return objectMapper.convertValue(ENDERECOPESSOA1, EnderecoDTO.class);
    }

    public ENDERECO_PESSOA findAdressByID(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findAll()
                .stream()
                .filter(ENDERECO_PESSOA -> ENDERECO_PESSOA.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow( () -> new RegraDeNegocioException("Id de ENDERECO_PESSOA não cadastrado no sistema!") );
    }


    public void delete(Integer id) throws RegraDeNegocioException, TipoRequisicaoInvalido {

        log.info("Deletando endereco!");
        enderecoRepository.delete(findAdressByID(id));
    }

}
