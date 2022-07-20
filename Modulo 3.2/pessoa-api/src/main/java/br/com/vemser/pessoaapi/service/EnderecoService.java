package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.exceptions.TipoRequisicaoInvalido;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public EnderecoDTO create(Integer id_pessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Criando ENDERECOPESSOA!");
        EnderecoEntity ENDERECOPESSOA = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        PessoaEntity pessoaEntityRetornadaPorID = pessoaService.findPersonByID(id_pessoa);
//        emailService.sendEmail(pessoaEntityRetornadaPorID.getNome(), id_pessoa, pessoaEntityRetornadaPorID.getEmail(), POST);
        ENDERECOPESSOA.setId_pessoa(id_pessoa);
        EnderecoEntity ENDERECOPESSOA1 = enderecoRepository.save(ENDERECOPESSOA);
        return objectMapper.convertValue(ENDERECOPESSOA, EnderecoDTO.class);
    }

    // O endereço é recebido como path variable e altera as informações
    // do mesmo, sem mudar o id da pessoa!
    public EnderecoDTO update(Integer id, EnderecoCreateDTO enderecoCreateDTOAtualizar) throws RegraDeNegocioException, TipoRequisicaoInvalido {
        log.info("Atualizando endereco!");

        Integer id_pessoa = findAdressByID(id).getId_pessoa();
        PessoaEntity pessoaEntity = pessoaService.findPersonByID(id_pessoa);

        // Se o endereco existe ele retorna, senao ele estoura a exception
        EnderecoEntity ENDERECOPESSOARecuperado = findAdressByID(id);
        EnderecoEntity ENDERECOPESSOAAtualizar = objectMapper.convertValue(enderecoCreateDTOAtualizar, EnderecoEntity.class);

//        emailService.sendEmail(pessoaEntity.getNome(), id, pessoaEntity.getEmail(), PUT);


        ENDERECOPESSOARecuperado.setTipo(ENDERECOPESSOAAtualizar.getTipo());
        ENDERECOPESSOARecuperado.setLogradouro(ENDERECOPESSOAAtualizar.getLogradouro());
        ENDERECOPESSOARecuperado.setNumero(ENDERECOPESSOAAtualizar.getNumero());
        ENDERECOPESSOARecuperado.setComplemento(ENDERECOPESSOAAtualizar.getComplemento());
        ENDERECOPESSOARecuperado.setCidade(ENDERECOPESSOAAtualizar.getCidade());
        ENDERECOPESSOARecuperado.setEstado(ENDERECOPESSOAAtualizar.getEstado());
        ENDERECOPESSOARecuperado.setPais(ENDERECOPESSOAAtualizar.getPais());

        EnderecoEntity ENDERECOPESSOA1 = enderecoRepository.save(ENDERECOPESSOARecuperado);

        return objectMapper.convertValue(ENDERECOPESSOA1, EnderecoDTO.class);
    }

    public EnderecoEntity findAdressByID(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findAll()
                .stream()
                .filter(EnderecoEntity -> EnderecoEntity.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow( () -> new RegraDeNegocioException("Id de EnderecoEntity não cadastrado no sistema!") );
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
