package br.com.vemser.pessoaapi.dto.Endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    @Schema(description = "ID do endereco")
    private Integer idEndereco;


}
