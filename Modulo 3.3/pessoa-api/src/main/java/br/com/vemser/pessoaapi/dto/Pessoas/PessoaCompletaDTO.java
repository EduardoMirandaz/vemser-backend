package br.com.vemser.pessoaapi.dto.Pessoas;

import br.com.vemser.pessoaapi.dto.Contato.ContatoDTO;
import br.com.vemser.pessoaapi.dto.Endereco.EnderecoDTO;
import br.com.vemser.pessoaapi.dto.Pet.PetDTO;
import lombok.Data;

import java.util.List;

@Data
public class PessoaCompletaDTO extends PessoaDTO{

    private List<EnderecoDTO> enderecos;
    private List<ContatoDTO> contatos;
    private PetDTO pet;

}
