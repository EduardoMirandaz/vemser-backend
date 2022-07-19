package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.client.DadosPessoaisClient;
import br.com.vemser.pessoaapi.dto.DadosPessoaisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosPessoaisService {

    @Autowired
    private DadosPessoaisClient client;

    public List<DadosPessoaisDTO> listaDadosPessoais(){
        return client.getAll();
    }

    public DadosPessoaisDTO create(DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
        return client.post(dadosPessoaisDTO);
    }

    public DadosPessoaisDTO update(String cpf, DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
        return client.put(cpf, dadosPessoaisDTO);
    }

    public DadosPessoaisDTO getByCpf (String cpf){
        return client.get(cpf);
    }

    public void delete(String cpf){
        client.delete(cpf);
    }
}