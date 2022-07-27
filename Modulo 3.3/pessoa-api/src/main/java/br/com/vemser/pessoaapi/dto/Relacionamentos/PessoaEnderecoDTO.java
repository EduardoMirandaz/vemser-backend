package br.com.vemser.pessoaapi.dto.Relacionamentos;
import br.com.vemser.pessoaapi.dto.Endereco.EnderecoDTO;
import br.com.vemser.pessoaapi.dto.Pessoas.PessoaDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class PessoaEnderecoDTO extends PessoaDTO {
    @Schema(description = "Relacionamento enderecos com pessoa")
    private List<EnderecoDTO> enderecos;
}