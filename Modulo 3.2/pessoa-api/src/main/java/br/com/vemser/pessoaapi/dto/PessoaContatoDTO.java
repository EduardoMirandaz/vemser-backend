package br.com.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class PessoaContatoDTO extends PessoaDTO {
    @Schema(description = "Relacionamento contatos com pessoa")
    private List<ContatoDTO> contatos;
}