package br.com.vemser.pessoaapi.dto.Endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    @Schema(description = "ID do endereco")
    private Integer idEndereco;

    @NotNull(message = "O id do morador não pode ser nulo!")
    @Schema(description = "ID do morador")
    private Integer idPessoa;


}
